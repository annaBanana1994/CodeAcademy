package com.project.finance.model;

import javax.persistence.*;

@Entity
@Table (name = "Accounts")
@SequenceGenerator(name="seqAccount", initialValue=1, allocationSize=1000)
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqAccount")
    @Column
    private int id;
    @Column
    private String accountName;
    @Column
    private String accountType;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User user;

    public Account(){}

    public Account(String accountName, AccountType accountType, User user) {
        this.accountName = accountName;
        this.accountType = accountType.name();
        this.user =user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountType=" + accountType +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType.name();
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
