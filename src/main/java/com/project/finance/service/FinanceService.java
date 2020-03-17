package com.project.finance.service;

import com.project.finance.repository.DatabaseService;
import com.project.finance.model.Account;
import com.project.finance.model.User;
import com.project.finance.utilities.NoRecordsExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceService {

    @Autowired
    DatabaseService databaseService;

    public List<User> getAllUsers() {
        //TODO catch null
        return databaseService.getAllUsersInDB();
    }

    public User getUsersInformationById(int id){
        //TODO need to decide how to display error message in browser
        try {
            User user = databaseService.searchForUserByPK(id);
            return user;
        }catch (NoRecordsExist ex){
            return null;
        }
    }

    public List<User> getUsersInformationByFirstName(String firstName){
        //TODO need to decide how to display error message in browser
        try {
            List<User> users = databaseService.searchForUserByFirstName(firstName);
            return users;
        }catch (RuntimeException ex){
            return null;
        }
    }

    public String searchUsersAccountsForSpecific(int id, String accountType){

        //TODO catch null user
        try {
            User user = databaseService.searchForUserByPK(id);
            List<Account> searchedForAccounts = new ArrayList<>();
            for (Account account : user.getAccounts()) {
                if (account.getAccountType().toString().equalsIgnoreCase(accountType)) {
                    searchedForAccounts.add(account);
                }
            }
            //ToDo error message if doesn't exist
            if(searchedForAccounts.isEmpty()){
                return userDoesNotHaveThatAccount(user);
            }
            return searchedForAccounts.toString();
        }catch (RuntimeException ex){
            return userDoesNotExist();
        }
    }

    private String userDoesNotHaveThatAccount(User user) {
        return user.getFirstName()+' '+user.getLastName()+" does not have any accounts of that type";
    }

    public String addNewUser(User user) {
        databaseService.addNewUser(user);
        return  "New user added: "+user.toString();

    }


    //TODO Should create an error message instead
    public String userDoesNotExist(){
        return "User is not in our records";
    }
    //add account?
    //add user

}

