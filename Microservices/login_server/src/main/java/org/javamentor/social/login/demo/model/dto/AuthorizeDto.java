package org.javamentor.social.login.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javamentor.social.login.demo.model.Account;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@AllArgsConstructor
@Data
public class AuthorizeDto {

    @NotNull(message = "Токен не может быть пустым!")
    private String jwtToken;

    @NotEmpty
    private Account account;
}
