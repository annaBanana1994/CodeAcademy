package com.project.finance.controller;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.project.finance.model.User;
import com.project.finance.response.ResponseOfUser;
import com.project.finance.service.FinanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> searchForAllUsers (){
        return new ResponseEntity<>(financeService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/search/{userFirstName}")
    public ResponseEntity<List<?>> searchforUserByFirstName (@PathVariable("userFirstName")String firstName){
        return new ResponseEntity<>(financeService.getUsersInformationByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOfUser> searchForUserById(@PathVariable("id")int id){
        return new ResponseEntity<>(financeService.getUsersInformationById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/{accountType}")
    public ResponseEntity<ResponseOfUser> checkIfUserHasAccountType(@PathVariable("id")int id,
                                                            @PathVariable("accountType") String accountType){
        return new ResponseEntity<>(financeService.searchUsersAccountsForSpecific(id, accountType), HttpStatus.OK);
    }

    @PostMapping(value = "/newUser")
            //, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseOfUser> addNewUser(@RequestBody String name){
        return new ResponseEntity<>(financeService.addNewUser(name),HttpStatus.CREATED);
    }

    @PostMapping("/{id}/newAccount")
    public ResponseEntity<ResponseOfUser> addNewAccount(@PathVariable("id")int id, @RequestBody String accountInformation){
        return new ResponseEntity<>(financeService.addNewAccount(id, accountInformation), HttpStatus.CREATED);
    }

    @PutMapping("update/user/{id}")
    public ResponseEntity<ResponseOfUser> updateUser(@PathVariable("id")int id, @RequestBody String name){
        return new ResponseEntity<>(financeService.updateUser(id, name), HttpStatus.OK);
    }

    @PutMapping("update/account/{id}")
    public ResponseEntity<?> updateAccountName(@PathVariable("id")int id, @RequestBody String name){
        if(financeService.updateAccountName(id, name)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{type}")
    public ResponseEntity<Object> deleteUser(@PathVariable("type")String type, @RequestBody String id) throws InvalidDefinitionException {
        return new ResponseEntity<>(financeService.deleteRecord(type, id), HttpStatus.OK);
    }
}

