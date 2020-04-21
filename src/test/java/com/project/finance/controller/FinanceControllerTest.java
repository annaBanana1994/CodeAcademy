package com.project.finance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.project.finance.TestData;
import com.project.finance.service.FinanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FinanceControllerTest {

    @InjectMocks
    FinanceController financeController;

    @Mock
    FinanceService financeService;

    @Test
    public void controllerPresent(){
        assertThat(financeController).isNotNull();
    }

    @Test
    public void returnsAllUsers() {
        when(financeService.getAllUsers()).thenReturn((TestData.getUserTestData()));
        ResponseEntity<?> responseEntity = financeController.searchForAllUsers();
        assertTrue(responseEntity.getBody().toString().contains("John Smith"));
        assertTrue(responseEntity.getBody().toString().contains("John Stone"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void searchforUserByFirstName() {
        when(financeService.getUsersInformationByFirstName(isA(String.class))).thenReturn(TestData.getUserTestData());
        ResponseEntity<?> responseEntity = financeController.searchforUserByFirstName("John");
        assertTrue(responseEntity.getBody().toString().contains("John Smith"));
        assertTrue(responseEntity.getBody().toString().contains("John Stone"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void searchForUserById() throws Exception{
        when(financeService.getUsersInformationById(isA(Integer.class))).thenReturn(TestData.userResponse());
        ResponseEntity<?> responseEntity = financeController.searchForUserById(0);
        assertTrue(asString(responseEntity.getBody()).contains("\"firstName\":\"Bob\""));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void checkIfUserHasAccountType_True() {
        when(financeService.searchUsersAccountsForSpecific(isA(Integer.class),isA(String.class))).thenReturn(TestData.userResponseWithCurrentAccount());
        ResponseEntity<?> responseEntity = financeController.checkIfUserHasAccountType(0, "current");
        assertTrue(asString(responseEntity.getBody()).contains("\"firstName\":\"Bob\""));
        assertTrue(asString(responseEntity.getBody()).contains("Bob Smith's current Account"));
        assertTrue(asString(responseEntity.getBody()).contains("current"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void checkIfUserHasAccountType_False() {
        when(financeService.searchUsersAccountsForSpecific(isA(Integer.class),isA(String.class))).thenReturn(TestData.userResponse());
        ResponseEntity<?> responseEntity = financeController.checkIfUserHasAccountType(0, "checking");
        assertTrue(asString(responseEntity.getBody()).contains("\"firstName\":\"Bob\""));
        assertFalse(asString(responseEntity.getBody()).contains("Bob Smith's checking Account"));
        assertFalse(asString(responseEntity.getBody()).contains("checking"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void addNewUser_CorrectNameEntry() {
        when(financeService.addNewUser(isA(String.class))).thenReturn(TestData.userResponse());
        ResponseEntity<?> responseEntity = financeController.addNewUser("Bob Smith");
        assertTrue(asString(responseEntity.getBody()).contains("\"firstName\":\"Bob\""));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void addNewAccount_success() {
        when(financeService.addNewAccount(isA(Integer.class), isA(String.class))).thenReturn(TestData.userResponseWithCurrentAccount());
        ResponseEntity<?> responseEntity = financeController.addNewAccount(1, "current");
        assertTrue(asString(responseEntity.getBody()).contains("\"firstName\":\"Bob\""));
        assertTrue(asString(responseEntity.getBody()).contains("Bob Smith's current Account"));
        assertTrue(asString(responseEntity.getBody()).contains("current"));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void updateUser() {
        when(financeService.updateUser(isA(Integer.class),isA(String.class))).thenReturn(TestData.userResponseWithCurrentAccount());
        ResponseEntity<?> responseEntity = financeController.updateUser(1, "Bob Smith");
        assertTrue(asString(responseEntity.getBody()).contains("Bob Smith"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateAccountName_success() {
        when(financeService.updateAccountName(isA(Integer.class), isA(String.class))).thenReturn(true);
        ResponseEntity<?> responseEntity = financeController.updateAccountName(1, "checking");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void updateAccountName_fail() {
        when(financeService.updateAccountName(isA(Integer.class), isA(String.class))).thenReturn(false);
        ResponseEntity<?> responseEntity = financeController.updateAccountName(1, "checking");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void deleteUser_success() throws InvalidDefinitionException {
        when(financeService.deleteRecord(isA(String.class), isA(String.class))).thenReturn(TestData.getTestUser());
        ResponseEntity<?> responseEntity = financeController.deleteUser("user", "0");
        assertTrue(asString(responseEntity.getBody()).contains("\"firstName\":\"Bob\""));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    public static String asString(final Object obj) {
        try {
            String objs = new ObjectMapper().writeValueAsString(obj);
            return objs;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

}