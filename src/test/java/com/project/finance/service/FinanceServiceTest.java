package com.project.finance.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.finance.TestData;
import com.project.finance.model.Account;
import com.project.finance.model.AccountType;
import com.project.finance.model.User;
import com.project.finance.repository.AccountRepository;
import com.project.finance.repository.UserRepository;
import com.project.finance.response.ResponseOfUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FinanceServiceTest {

    @InjectMocks
    FinanceService financeService;

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void getAllUsers_ReturnsListOfAllUsers() {
        when(userRepository.findAll()).thenReturn(TestData.getUserTestData());
        List<User> users = financeService.getAllUsers();
        assertEquals(users.get(0).getLastName(), "Smith");
        assertEquals(users.get(1).getLastName(), "Stone");
    }

    @Test
    public void getUsersInformationByFirstName_UsersExist() {
        when(userRepository.findByFirstName(isA(String.class))).thenReturn(TestData.getUserTestData());
        List<User> users = financeService.getUsersInformationByFirstName("John");
        assertEquals(users.get(0).getFirstName(), "John");
        assertEquals(users.get(1).getFirstName(), "John");
    }
    @Test
    public void getUsersInformationByFirstName_UserDoesNotExist() {
        when(userRepository.findByFirstName(isA(String.class))).thenReturn(Collections.emptyList());
        List<User> users = financeService.getUsersInformationByFirstName("Bob");
        assertTrue(financeService.getUsersInformationByFirstName("Bob").isEmpty());
    }

    @Test
    public void getUsersInformationById_UserExists_WithAccounts() {
        when(userRepository.findById(0)).thenReturn(TestData.getTestUser());
        when(accountRepository.findByUser(isA(User.class))).thenReturn(TestData.listOfAccounts());
        ResponseOfUser response = financeService.getUsersInformationById(0);
        assertTrue(response.getUser().getFirstName().equals("Bob"));
        assertTrue(response.getAccounts().get(0).getUser().getFirstName().equals("Bob"));
        assertTrue(response.getAccounts().get(0).getAccountType().equals(AccountType.CURRENT.toString()));
        assertTrue(response.getAccounts().get(1).getAccountType().equals(AccountType.CHECKING.toString()));
    }

    @Test
    public void getUsersInformationById_UserExists_WithNoAccounts() {
        when(userRepository.findById(0)).thenReturn(TestData.getTestUser());
        when(accountRepository.findByUser(isA(User.class))).thenReturn(Collections.emptyList());
        ResponseOfUser response = financeService.getUsersInformationById(0);
        assertTrue(response.getUser().getFirstName().equals("Bob"));
        assertTrue(response.getAccounts().isEmpty());
    }

    @Test
    public void getUsersInformationById_UserDoesNotExist_WithNoAccounts() throws ResponseStatusException {
        when(userRepository.findById(0)).thenReturn(null);
    }

    @Test
    public void searchUsersAccountsForSpecific() {
    }

//    public ResponseOfUser searchUsersAccountsForSpecific(int id, String accountType){
//        User user = userRepository.findById(id);
//        List<Account> accountsOfSpecifiedType =accountRepository.findByUserAndAccountType(user, accountStringEnumToString(accountType));
//        ResponseOfUser responseOfUser = new ResponseOfUser(user, accountsOfSpecifiedType);
//        return  responseOfUser;
//    }

    @Test
    public void addNewUser() {
    }

    @Test
    public void addNewAccount() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void updateAccountName() {
    }

    @Test
    public void deleteRecord() {
    }

    @Test
    public void iterableToList() {
    }

    @Test
    public void accountStringEnumToString() {
    }


    //Dont think this needs to be tested in the controller
//    @Test
//    public void addNewUser_IncorrectNameEntry(){
//        when(financeService.addNewUser(isA(String.class))).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
//        assertEquals(HttpStatus.BAD_REQUEST, financeController.addNewUser("Bob").getStatusCode());
//    }
//    @Test
//    public void addNewUser_eroorWithAddingToDB(){
//        when(financeService.addNewUser(isA(String.class))).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
//        assertEquals(HttpStatus.NOT_FOUND, financeController.addNewUser("Bob").getStatusCode());
//    }

    public static String asJsonString(final Object obj) {
        try {
            String objs = new ObjectMapper().writeValueAsString(obj);
            return objs;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

}