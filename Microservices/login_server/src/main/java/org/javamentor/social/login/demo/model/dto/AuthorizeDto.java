package org.javamentor.social.login.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javamentor.social.login.demo.model.Account;

@Data
@AllArgsConstructor
public class AuthorizeDto {

    private String jwtToken;
    private Account account;
}
