package org.javamentor.social.login.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.dto.AuthorizeDto;
import org.javamentor.social.login.demo.model.request.AuthRequest;
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
    public String getEmail(@RequestBody Long userId) {
        String email = accountService.getUserEmailByUserId(userId);
        if (email != null) {
            return email;

        } else {
            throw new NoSuchUserException("No account with id " + userId);
        }
    }

}