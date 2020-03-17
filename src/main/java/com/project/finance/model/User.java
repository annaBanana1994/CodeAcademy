package com.project.finance.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private List<Account> accounts;

    public User(String firstName, String lastName, List<Account> accounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }

    public void addAccount(Account newAccount){
        accounts.add(newAccount);
    }

    @Override
    public String toString() {
        return "User{" +
                " Full Name='" + firstName + " " + lastName + '\''+
                ", Accounts=" + accounts +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

}
