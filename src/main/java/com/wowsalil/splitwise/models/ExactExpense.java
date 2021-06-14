package com.wowsalil.splitwise.models;


import java.util.Map;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public class ExactExpense extends Expense {
    private Map<String, Double> userIdVsExpense;

    public ExactExpense(Map<String, Double> userIdVsExpense,
                        Double amount,
                        String expenseReason,
                        String ownerId) {
        super(amount, expenseReason, ExpenseDivisionType.EXACT, ownerId);
        this.userIdVsExpense = userIdVsExpense;
    }

    public Map<String, Double> getUserIdVsExpense() {
        return userIdVsExpense;
    }
}
