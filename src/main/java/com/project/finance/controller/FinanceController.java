package com.project.finance.controller;

import com.project.finance.model.User;
import com.project.finance.service.FinanceService;
import com.project.finance.utilities.NullStatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class FinanceController {

    //TODO At interfaces to servicelayer and repository, add logger files

    @Autowired
    FinanceService financeService;

    @GetMapping("/records")
    public ResponseEntity<List<User>> searchAllRecords (){
        return new ResponseEntity<>(financeService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchForUserById(@PathVariable("id")int id){
        //TODO We don't want this logic here, move to servic layer
        User user = financeService.getUsersInformationById(id);
        if (user==null){
            //return new ResponseEntity<>(new CustomErrorMessage("No user with "+id+"  found in our records."),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>("No user with "+id+"  found in our records.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userFirstName}")
    public ResponseEntity<List<User>> searchforUserByFirstName (@PathVariable("userFirstName")String firstName){
        return new ResponseEntity<>(financeService.getUsersInformationByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/{id}/{accountType}")
    public ResponseEntity<String> checkIfUserHasAccountType(@PathVariable("id")int id,
                                                            @PathVariable("accountType")String accountType){

        return new ResponseEntity<>(financeService.searchUsersAccountsForSpecific(id, accountType), HttpStatus.OK);
    }

    //{/new body will have the user information}

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addNewUserInformation(@RequestBody User user){
        financeService.addNewUser(user);
        return null;
    }

//    @DeleteMapping()
//    public RequestEntity<String> deleteUser(@RequestBody String firstName){
//
//        return null;
//    }


}
