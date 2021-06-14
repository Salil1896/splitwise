package com.wowsalil.splitwise.service;

import com.wowsalil.splitwise.models.EqualExpense;
import com.wowsalil.splitwise.models.ExactExpense;
import com.wowsalil.splitwise.models.Expense;
import com.wowsalil.splitwise.models.ExpenseDivisionType;
import com.wowsalil.splitwise.models.PercentageExpense;
import com.wowsalil.splitwise.models.User;
import com.wowsalil.splitwise.models.UserExpensesRelation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public class ExpenseServiceImpl implements ExpenseService {

    private UserManagementService userManagementService;
    private Map<String, Expense> expenseMap;

    public ExpenseServiceImpl(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
        this.expenseMap = new HashMap<>();
    }

    @Override
    public void addExpense(Expense expense) throws Exception {
        String expenseId = "EX" + UUID.randomUUID().toString();
        Map<String, Double> userIdVsShare = userIdVsShare(expense);
        User owner = userManagementService.getUser(null, null, expense.getOwnerId());
        userIdVsShare.entrySet().forEach(kv -> {
            if (!kv.getKey().equals(expense.getOwnerId())) {
                User second = userManagementService.getUser(null, null, kv.getKey());

                UserExpensesRelation userExpensesRelation = new UserExpensesRelation(expenseId, kv.getValue(), owner.getUserId(), second.getUserId());
                userManagementService.addUserExpenseRelation(userExpensesRelation, owner);
                userManagementService.addUserExpenseRelation(userExpensesRelation, second);
            }
        });
        expenseMap.put(expenseId, expense);
    }

    @Override
    public Map<String, Double> userIdVsBalance(String userId) {
        return userManagementService.userIdVsBalance(userId);
    }

    private Map<String, Double> userIdVsShare(Expense expense) throws Exception {
        return expense.getDivisionType().visit(new ExpenseDivisionType.Visitor<Map<String, Double>>() {
            @Override
            public Map<String, Double> visitEqual() {
                EqualExpense equalExpense = (EqualExpense) expense;
                return equalExpense.getUserIds().stream().collect(Collectors.toMap(
                        x -> x, x -> equalExpense.getAmount() / equalExpense.getUserIds().size()));
            }

            @Override
            public Map<String, Double> visitExact() throws Exception {
                ExactExpense exactExpense = (ExactExpense) expense;
                Double sum = exactExpense.getUserIdVsExpense().values().stream().reduce(0.0, Double::sum);
                if (!sum.equals(exactExpense.getAmount())) {
                    throw new Exception("Invalid Input");
                }
                return null;
            }

            @Override
            public Map<String, Double> visitPercent() throws Exception {
                PercentageExpense percentageExpense = (PercentageExpense) expense;
                Double percentageSum = percentageExpense.getUserIdVsPct().values().stream().reduce(0.0, Double::sum);
                if (!percentageSum.equals(100.0)) {
                    throw new Exception("Invalid Input");
                }
                return percentageExpense.getUserIdVsPct().entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey, e -> (e.getValue() / 100) * percentageExpense.getAmount()));
            }
        });
    }
}
