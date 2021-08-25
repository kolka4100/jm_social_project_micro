package org.javamentor.social.login.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javamentor.social.login.demo.model.AccountStatus;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class AccountDTO {

    @NotEmpty
    private AccountStatus status;

}
