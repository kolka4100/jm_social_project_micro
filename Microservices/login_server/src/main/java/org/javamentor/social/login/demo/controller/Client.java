package org.javamentor.social.login.demo.controller;

import org.javamentor.social.login.demo.model.dto.AuthorizeDto;
import org.javamentor.social.login.demo.model.request.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Client {

    @PostMapping("/login")
    AuthorizeDto auth(AuthRequest request);

    @PostMapping("/registration")
    void register(@RequestBody AuthRequest request);

}
