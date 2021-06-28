package org.javamentor.social.email_service.rest_controllers;

import org.javamentor.social.email_service.model.dto.MailDto;
import org.javamentor.social.email_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email-service")
public class EmailServiceController {

   private EmailService emailService;

   @Autowired
   public EmailServiceController(EmailService emailService) {
       this.emailService = emailService;
   }


   @PostMapping("/send-greetingnemail")
   public void sendEmail(@RequestBody String email){
       emailService.sendGreetingHtmlEmail(email);
   }

   @PostMapping("/send-mail")
   public void sendEmail(@RequestBody MailDto mailDto){
     emailService.sendEmail(mailDto.getEmail(), mailDto.getSubject(), mailDto.getText());
   }

   @PostMapping("/send-greeting-htmlemail")
   public void sendGreetingHtmlEmail(@RequestBody String email){
       emailService.sendGreetingHtmlEmail(email);
   }

   @PostMapping("/send-registration-notification")
   public void sendRegistrationNotification(@RequestBody String email,
                                            @RequestBody String firstName,
                                            @RequestBody String lastName){
       emailService.sendRegistrationNotification(email, firstName, lastName);
   }

    @PostMapping("/send-payment-notification")
    public void sendPaymentNotification(@RequestBody String email,
                                        @RequestBody int lastDays,
                                        @RequestBody String firstName,
                                        @RequestBody String lastName){
       emailService.sendPaymentNotification(email, lastDays, firstName, lastName);
    }
}


