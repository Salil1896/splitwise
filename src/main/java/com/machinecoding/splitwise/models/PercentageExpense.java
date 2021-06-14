package com.machinecoding.splitwise.models;


import java.util.Map;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public class PercentageExpense extends Expense {
    private Map<String, Double> userIdVsPct;

    public PercentageExpense(Map<String, Double> userIdVsPct,
                             Double amount,
                             String expenseReason,
                             String ownerId) {
        super(amount, expenseReason, ExpenseDivisionType.PERCENT, ownerId);
        this.userIdVsPct = userIdVsPct;
    }

    public Map<String, Double> getUserIdVsPct() {
        return userIdVsPct;
    }
}
