package com.project.finance;

import com.project.finance.model.Account;
import com.project.finance.model.AccountType;
import com.project.finance.model.User;
import com.project.finance.response.ResponseOfUser;
import org.springframework.http.ResponseEntity;

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
        users.add(user1);
        users.add(user2);
        return users;
    }

    public static ResponseOfUser userResponse(){
        User user = new User("Bob","Smith");
        ResponseOfUser response = new ResponseOfUser(user,null);
        return response;
    }
    public static ResponseOfUser userResponseWithCurrentAccount(){
        User user = new User("Bob","Smith");
        Account currentAccount = new Account("Bob Smith's current Account", AccountType.CURRENT, user);
        List<Account> accounts = new ArrayList<>();
        accounts.add(currentAccount);
        ResponseOfUser response = new ResponseOfUser(user,accounts);
        return response;
    }

    public static List<Account> listOfAccounts(){
        User user = new User("Bob","Smith");
        Account currentAccount = new Account("Bob Smith's current Account", AccountType.CURRENT, user);
        Account checkingAccount = new Account("Bob Smith's checking Account", AccountType.CHECKING, user);
        List<Account> accounts = new ArrayList<>();
        accounts.add(currentAccount);
        accounts.add(checkingAccount);
        return accounts;
    }

}
