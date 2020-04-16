package com.project.finance.service;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.project.finance.model.AccountType;
import com.project.finance.repository.AccountRepository;
import com.project.finance.model.Account;
import com.project.finance.model.User;
import com.project.finance.repository.UserRepository;
import com.project.finance.response.ResponseOfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FinanceService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;


    public List<?> getAllUsers() {
        return iterableToList(userRepository.findAll());
    }

    public List<User> getUsersInformationByFirstName(String firstName){
            return (userRepository.findByFirstName(firstName));
    }

    public ResponseOfUser getUsersInformationById(int id){
        User user = userRepository.findById(id);
        if(user==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        List<Account> accounts= accountRepository.findByUser(user);
        ResponseOfUser responseOfUser = new ResponseOfUser(user, accounts);
        return responseOfUser;
    }

    public ResponseOfUser searchUsersAccountsForSpecific(int id, String accountType){
        User user = userRepository.findById(id);
        List<Account> accountsOfSpecifiedType =accountRepository.findByUserAndAccountType(user, accountStringEnumToString(accountType));
        ResponseOfUser responseOfUser = new ResponseOfUser(user, accountsOfSpecifiedType);
        return  responseOfUser;
    }

    public ResponseOfUser addNewUser(String name) {
        int numberOfNames= name.lastIndexOf(' ');
        if (numberOfNames==-1){
            throw new ResponseStatusException((HttpStatus.BAD_REQUEST));
        }

        User newUser = new User(name.substring(0,numberOfNames), name.substring(numberOfNames+1));
        return getUsersInformationById(userRepository.save(newUser).getId());
    }

    public ResponseOfUser addNewAccount(int id, String accountInformation) {
        String lines[] = accountInformation.split("\\r?\\n");
        User user = userRepository.findById(id);
        if(user==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        Account newAccount = new Account(lines[0], accountToEnum(lines[1]), user);
        accountRepository.save(newAccount);
        return getUsersInformationById(user.getId());
    }

    public ResponseOfUser updateUser(int id, String name) {
        int numberOfNames= name.lastIndexOf(' ');
        if (numberOfNames==-1){
            throw new ResponseStatusException((HttpStatus.BAD_REQUEST));
        }
        User existingUser = userRepository.findById(id);
        existingUser.setFirstName(name.substring(0,numberOfNames));
        existingUser.setLastName(name.substring(numberOfNames+1));
        userRepository.save(existingUser);
        return getUsersInformationById(existingUser.getId());
    }

    public void updateAccountName(int id, String name) {
        Account existingAccount = accountRepository.findById(id);
        existingAccount.setAccountName(name);
        System.out.println(existingAccount);
        accountRepository.save(existingAccount);
        User user= userRepository.findById(existingAccount.getUser().getId());
        int iduser = user.getId();
        ResponseOfUser response = getUsersInformationById(iduser);
      //  return getUsersInformationById(iduser);
    }

    public Object deleteRecord(String type, String idString) throws InvalidDefinitionException {
        try {
            int id = Integer.parseInt(idString.trim());

            if (type.equals("user")) {
                User userToBeDeleted = userRepository.findById(id);
                List<Account> accountsToBeDeleted = accountRepository.findByUser(userToBeDeleted);
                for (Account account : accountsToBeDeleted) {
                    accountRepository.delete(account);
                }
                userRepository.delete(userToBeDeleted);
                return userToBeDeleted;
            } else if (type.equals("account")) {
                    Account accountToBeDeleted = accountRepository.findById(id);
                    accountRepository.delete(accountToBeDeleted);
                    return accountToBeDeleted;
            }
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return null;
    }









    public List<?> iterableToList(Iterable iterable){
        List<Object> list = new ArrayList<>();
        Iterator<?> iterator= iterable.iterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    public String accountStringEnumToString(String accountType){
        if (accountType.equalsIgnoreCase(AccountType.CURRENT.toString())){
            return AccountType.CURRENT.name();
        } else if (accountType.equalsIgnoreCase(AccountType.SAVINGS.toString())) {
            return AccountType.SAVINGS.name();
        } else if (accountType.equalsIgnoreCase(AccountType.CHECKING.toString())) {
            return AccountType.CHECKING.name();
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private AccountType accountToEnum(String accountType) {
        if (accountType.equalsIgnoreCase(AccountType.CURRENT.toString())) {
            return AccountType.CURRENT;
        } else if (accountType.equalsIgnoreCase(AccountType.SAVINGS.toString())) {
            return AccountType.SAVINGS;
        } else if (accountType.equalsIgnoreCase(AccountType.CHECKING.toString())) {
            return AccountType.CHECKING;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
