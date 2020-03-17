package com.project.finance.model;

public class Account {

    private String accountName;
    private AccountType accountType;

    public Account(String accountName, AccountType accountType) {
        this.accountName = accountName;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", accountType=" + accountType +
                '}';
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountNumber) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
