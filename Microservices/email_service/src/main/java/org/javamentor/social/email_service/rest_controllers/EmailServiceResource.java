package org.javamentor.social.email_service.rest_controllers;

import org.javamentor.social.email_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class EmailServiceResource {

   private EmailService emailService;

   @Autowired
   public EmailServiceResource(EmailService emailService) {
       this.emailService = emailService;
   }

   @GetMapping("/email_service")
   public EmailService getEmailService(){
       return emailService;
   }
}
