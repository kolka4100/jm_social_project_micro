package org.javamentor.social.login.demo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rest/data")
public class UserDataController{

    private final AccountService accountService;

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
}