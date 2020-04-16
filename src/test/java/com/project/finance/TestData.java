package com.project.finance;

import com.project.finance.model.User;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static User getTestUser(){
        User user = new User("Bob", "Smith");
        return user;
    }

    public static List<User> getUserTestData(){
        User user1 = new User("John", "Smith");
        User user2 = new User("John", "Stone");
        List<User> users = new ArrayList<>();
        users.add(getTestUser());
        users.add(user1);
        users.add(user2);
        return users;
    }
}
