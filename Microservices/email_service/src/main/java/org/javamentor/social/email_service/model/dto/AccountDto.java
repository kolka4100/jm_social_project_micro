package org.javamentor.social.email_service.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamentor.social.email_service.model.entity.AccountPrivilegeStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountPrivilegeStatus accountPrivilegeStatus;

    private LocalDate dateOfCreation;
}
