package com.machinecoding.splitwise.models;


import java.util.List;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public class EqualExpense extends Expense {
    private List<String> userIds;

    public EqualExpense(List<String> userIds,
                        Double amount,
                        String expenseReason,
                        String ownerId) {
        super(amount, expenseReason, ExpenseDivisionType.EQUAL, ownerId);
        this.userIds = userIds;
    }

    public List<String> getUserIds() {
        return userIds;
    }
}
