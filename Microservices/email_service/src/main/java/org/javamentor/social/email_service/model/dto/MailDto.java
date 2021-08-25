package org.javamentor.social.email_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {

   @NotNull
   @Email(message = "Введите корректный адрес электронной почты!")
   private String email;

   @NotBlank(message = "Введите тему сообщения")
   @Max(value = 50)
   private String subject;

   @NotBlank(message = "Введите сообщение")
   @Max(value = 300)
   private String text;
}
