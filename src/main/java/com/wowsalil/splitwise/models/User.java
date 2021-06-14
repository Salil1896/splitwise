package com.wowsalil.splitwise.models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public class User {
    private String email;
    private String phone;
    private String name;
    private String userId;

    private Map<String, UserExpensesRelation> expensesMap;

    public User(String email, String phone, String name, String userId) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.userId = userId;
        expensesMap = new HashMap<>();
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public boolean addUserExpenseRelation(UserExpensesRelation userExpensesRelation) {
        expensesMap.put(userExpensesRelation.getExpenseId(), userExpensesRelation);
        return true;
    }

    public Map<String, UserExpensesRelation> getExpensesMap() {
        return expensesMap;
    }
}
