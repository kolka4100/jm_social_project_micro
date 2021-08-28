package org.javamentor.social.login.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.javamentor.social.login.demo.model.dto.AuthorizeDto;
import org.javamentor.social.login.demo.model.request.AuthRequest;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/rest/auth")
public class AuthController implements Client{

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/login")
    @ApiOperation(value = "Authenticate users",
            notes = "Provide an email and password for authentication")
    public AuthorizeDto auth(@RequestBody AuthRequest request,
                             @RequestHeader Map<String, String> headers) {
        accountService.setLastVisitedDate(request, headers);
        return accountService.getAuthorizeDto(request);
    }


    @PostMapping("/registration")
    @ApiOperation(value = "Register users",
            notes = "Provide an email and password for registration")
    public void register(@RequestBody AuthRequest request) {
        accountService.register(request);
    }

}