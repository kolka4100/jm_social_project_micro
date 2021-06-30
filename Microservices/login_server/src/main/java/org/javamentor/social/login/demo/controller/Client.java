package org.javamentor.social.login.demo.controller;

import org.javamentor.social.login.demo.model.dto.AuthorizeDto;
import org.javamentor.social.login.demo.model.request.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("login-service")
public interface Client {

    @PostMapping("/auth")
    AuthorizeDto auth(AuthRequest request);

    @PostMapping("/register")
    void register(@RequestBody AuthRequest request);

}
