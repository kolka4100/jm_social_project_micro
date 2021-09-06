package org.javamentor.social.login.demo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.javamentor.social.login.demo.aspects.CheckAccount;
import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.request.TestDto;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/rest/data")
public class UserDataController {

    @Resource
    TestDto testDto;

    private final AccountService accountService;

    @Autowired
    public UserDataController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/email")
    @ApiOperation(value = "Find email by userId",
            notes = "Returns an email. If account not found returns NoSuchUserException")
    public String getEmail(@ApiParam(value = "id of user to return email", example = "13") @RequestBody Long userId) {
        String email = accountService.getUserEmailByUserId(userId);
        if (email != null) {
            return email;

        } else {
            throw new NoSuchUserException("No account with id " + userId);
        }
    }


    @GetMapping("/status/{userId}")
    @ApiOperation(value = "Find Status by userId",
            notes = "Returns a status")
    public String getStatus(@ApiParam(value = "id of user to return status", example = "13") @PathVariable("userId") Long userId) {
        return accountService.getStatusById(userId);
    }


    @PostMapping("/status/test")
    @CheckAccount(object = Account.class)
    public ResponseEntity<Account> testAccount(@RequestBody TestDto testDto) {
        Long a = testDto.getId();    //пытался вызвать aspect через вызов getId() С вложенным методом лажа полная.
        return new ResponseEntity<>(accountService.findById(a), HttpStatus.OK);
    }

}