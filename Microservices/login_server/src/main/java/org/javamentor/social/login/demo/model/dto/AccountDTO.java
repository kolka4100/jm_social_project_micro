package org.javamentor.social.login.demo.model.dto;

import lombok.Data;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.AccountStatus;

@Data
public class AccountDTO {

    private String email;
    private AccountStatus status;

    public AccountDTO(Account account) {
        this.email = account.getEmail();
        this.status = account.getStatus();
    }
}
