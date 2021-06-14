package com.machinecoding.splitwise.service;

import com.machinecoding.splitwise.models.User;
import com.machinecoding.splitwise.models.UserExpensesRelation;

import java.util.Map;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public interface UserManagementService {
    User addUser(String email, String phone, String name) throws Exception;

    User getUser(String email, String phone, String userId);

    boolean addUserExpenseRelation(UserExpensesRelation relation, User user);

    Map<String, Double> userIdVsBalance(String userId);
}
