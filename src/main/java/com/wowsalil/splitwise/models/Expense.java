package com.wowsalil.splitwise.models;


/**
 * @author salil.mamodiya
 * 26/04/21
 */
public abstract class Expense {
    private Double amount;
    private String expenseReason;
    private ExpenseDivisionType divisionType;
    private String ownerId;

    public Expense(Double amount, String expenseReason, ExpenseDivisionType divisionType, String ownerId) {
        this.amount = amount;
        this.expenseReason = expenseReason;
        this.divisionType = divisionType;
        this.ownerId = ownerId;
    }

    public ExpenseDivisionType getDivisionType() {
        return divisionType;
    }

    public Double getAmount() {
        return amount;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
