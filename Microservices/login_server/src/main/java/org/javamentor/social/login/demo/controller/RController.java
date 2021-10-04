package org.javamentor.social.login.demo.controller;

import org.javamentor.social.login.demo.model.request.AuthRequest;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Map;

@RestController
public class RController {

    private final AccountService accountService;

    @Autowired
    public RController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public Response auth(@RequestBody AuthRequest request,
                         @RequestHeader Map<String, String> headers) {
        accountService.setLastVisitedDate(request, headers);
        return Response.ok().build();
    }

    @PostMapping("/registration")
    public Response register(@RequestBody AuthRequest request) {
        accountService.register(request);
        return Response.ok().build();
    }

    @PostMapping("/email")
    public Response getEmail(@RequestBody Long userId){
        accountService.getUserEmailByUserId(userId);
        return Response.ok().build();
    }

    @GetMapping("/status/{userId}")
    public Response getStatus(@PathVariable("userId") Long userId){
        accountService.getStatusById(userId);
        return Response.ok().build();
    }
}
