package com.project.finance.response;

import com.project.finance.model.Account;
import com.project.finance.model.User;

import java.util.List;

public class ResponseOfUser {

    private User user;
    private List<Account> accounts;

    public ResponseOfUser(User user, List<Account> accounts) {
        this.user = user;
        this.accounts = accounts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
