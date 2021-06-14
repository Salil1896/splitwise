package com.wowsalil.splitwise.service;

import com.wowsalil.splitwise.models.User;
import com.wowsalil.splitwise.models.UserExpensesRelation;

import java.util.HashMap;
import java.util.Map;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author salil.mamodiya
 * 26/04/21
 */
public class UserManagementServiceImpl implements UserManagementService {

    private static final Map<String, User> phoneVsUser = new HashMap<>();
    private static final Map<String, User> userIdVsUser = new HashMap<>();
    private static final Map<String, User> emailVsUser = new HashMap<>();


    @Override
    public User addUser(String email, String phone, String name) throws Exception {
        if (emailVsUser.containsKey(email) || phoneVsUser.containsKey(phone)) throw new Exception("User Already Exist");
        String userId = "U" + UUID.randomUUID().toString();
        User user = new User(email, phone, name, userId);
        userIdVsUser.put(userId, user);
        if (Objects.nonNull(email)) {
            emailVsUser.put(email, user);
        }
        if (Objects.nonNull(phone)) {
            phoneVsUser.put(phone, user);
        }
        return user;
    }

    @Override
    public User getUser(String email, String phone, String userId) {
        if (Objects.nonNull(email)) return emailVsUser.get(email);
        if (Objects.nonNull(phone)) return phoneVsUser.get(phone);
        if (Objects.nonNull(userId)) return userIdVsUser.get(userId);
        return null;
    }

    @Override
    public boolean addUserExpenseRelation(UserExpensesRelation relation, User user) {
        return user.addUserExpenseRelation(relation);
    }

    @Override
    public Map<String, Double> userIdVsBalance(String userId) {
        User user = userIdVsUser.get(userId);
        Map<String, Double> userIdVsBalance = new HashMap<>();
        user.getExpensesMap().forEach((key, value) -> {
            if (!userIdVsBalance.containsKey(value.getSecond())) {
                userIdVsBalance.put(value.getSecond(), 0.0);
            }
            if (value.getOwner().equals(userId)) {
                userIdVsBalance.put(value.getSecond(), userIdVsBalance.get(value.getSecond()) + value.getAmount());
            } else {
                userIdVsBalance.put(value.getSecond(), userIdVsBalance.get(value.getSecond()) - value.getAmount());
            }
        });
        return userIdVsBalance.entrySet().stream().filter(x -> !x.getValue().equals(0.0)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


}
