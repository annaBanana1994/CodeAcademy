package com.project.finance.repository;

import com.project.finance.model.Account;
import com.project.finance.model.AccountType;
import com.project.finance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DatabaseService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsersInDB() {
        return null;
    }

    public User searchForUserByPK(int id){

        //database search by primary key
        User user = new User("Anna", "Edeleanu", new ArrayList<>());
        user.setId(1);
        //TODO when searching use equalsIgnoreCase
        user.setFirstName("Anna");
        user.setLastName("Edeleanu");
        Account account =new Account("Everyday Current account", AccountType.CURRENT);
        user.addAccount(account);
        return user;
    }

    public List<User> searchForUserByFirstName(String firstName){

        User user = new User("Anna", "Edeleanu", new ArrayList<>());
        user.setId(1);
        //TODO when searching use equalsIgnoreCase
        user.setFirstName("Anna");
        user.setLastName("Edeleanu");
        Account account =new Account("Everyday Current account", AccountType.CURRENT);
        user.addAccount(account);
        return null;
    }

    public void addNewUser(User user) {

    }


}
