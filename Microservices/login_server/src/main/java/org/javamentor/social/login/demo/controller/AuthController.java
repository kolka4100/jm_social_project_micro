package org.javamentor.social.login.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.javamentor.social.login.demo.exceptions.BlockUserException;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.dto.AuthorizeDto;
import org.javamentor.social.login.demo.model.request.AuthRequest;
import org.javamentor.social.login.demo.config.JwtTokenProvider;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/auth")
    @ApiOperation(value = "Authenticate users",
            notes = "Provide an email and password for authentication")
    public AuthorizeDto auth(@RequestBody AuthRequest request) {
        return accountService.getAuthorizeDto(request);
    }

    @PostMapping("/register")
    @ApiOperation(value = "Authenticate users",
            notes = "Provide an email and password for authentication")
    public void register(@RequestBody AuthRequest request) {
        accountService.register(request);
    }

}