package com.project.finance.controller;

import com.project.finance.TestData;
import com.project.finance.model.User;
import com.project.finance.service.FinanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FinanceControllerTest {

    @InjectMocks
    FinanceController financeController;

    @Mock
    FinanceService financeService;

    @Test
    public void returnsAllUsers() {
       // Mockito.when(financeService.getAllUsers()).thenReturn(TestData.getUserTestData());
        financeController.searchForAllUsers();

      //  Mockito.when(financeController.searchForAllUsers()).thenReturn((ResponseEntity<List<?>>) TestData.getUserTestData());



//        // when
//        Employees result = employeeController.getEmployees();
//
//        // then
//        assertThat(result.getEmployeeList().size()).isEqualTo(2);
//
//        assertThat(result.getEmployeeList().get(0).getFirstName())
//                .isEqualTo(employee1.getFirstName());
//
//        assertThat(result.getEmployeeList().get(1).getFirstName())
//                .isEqualTo(employee2.getFirstName());
    }

    @Test
    public void searchforUserByFirstName() {
    }

    @Test
    public void searchForUserById() {
    }

    @Test
    public void checkIfUserHasAccountType() {
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
    public void deleteUser() {
    }
}