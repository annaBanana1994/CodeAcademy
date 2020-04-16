package com.project.finance.model;

public enum AccountType {
    CURRENT ("Current Account"),
    SAVINGS ("Savings Account"),
    CHECKING ("Checking Account");

    private String name;

    private AccountType (String name){
        this.name=name;
    }
}
