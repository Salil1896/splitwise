package com.wowsalil.splitwise.service;

import com.wowsalil.splitwise.models.Expense;

import java.util.Map;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public interface ExpenseService {
    void addExpense(Expense expense) throws Exception;

    Map<String, Double> userIdVsBalance(String userId);
}
