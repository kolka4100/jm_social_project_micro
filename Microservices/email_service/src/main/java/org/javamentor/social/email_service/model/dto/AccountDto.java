package org.javamentor.social.email_service.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamentor.social.email_service.model.entity.AccountPrivilegeStatus;
import org.javamentor.social.email_service.valid_interface.ValidPassword;
import org.javamentor.social.email_service.valid_interface.ValueOfEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotNull
    @Email(message = "Введите корректный адрес электронной почты!")
    private String email;

    @NotNull
    @ValidPassword
    private String password;

    @NotEmpty
    @Enumerated(EnumType.STRING)  // хранение перечисления в базе в String
    @ValueOfEnum(enumClass = AccountPrivilegeStatus.class)  //проверка на valueOf
    private AccountPrivilegeStatus accountPrivilegeStatus;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCreation;
}
