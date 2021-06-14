package com.machinecoding.splitwise.models;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public class UserExpensesRelation {

    private String expenseId;
    private Double amount;
    private String owner;
    private String second;

    public UserExpensesRelation(String expenseId,
                                Double amount,
                                String owner,
                                String second) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.owner = owner;
        this.second = second;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecond() {
        return second;
    }

    public Double getAmount() {
        return amount;
    }
}
