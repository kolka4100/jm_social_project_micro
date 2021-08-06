package org.javamentor.social.login.demo.model.dto;

import lombok.Data;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.AccountStatus;

@Data
public class AccountDTO {

    private AccountStatus status;

    public AccountDTO(AccountStatus status) {
        this.status = status;
    }
}
