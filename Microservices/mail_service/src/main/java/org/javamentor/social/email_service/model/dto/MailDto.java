package org.javamentor.social.email_service.model.dto;


import lombok.Data;

@Data
public class MailDto {

   private String email;
   private String subject;
   private String text;
}
