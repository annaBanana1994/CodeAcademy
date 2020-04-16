package com.project.finance.service;

import com.project.finance.repository.AccountRepository;
import com.project.finance.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FinanceServiceTest {

    @InjectMocks
    FinanceService financeService;

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void getAllUsers() {
    }

    @Test
    public void getUsersInformationByFirstName() {
    }

    @Test
    public void getUsersInformationById() {
    }

    @Test
    public void searchUsersAccountsForSpecific() {
    }

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
}