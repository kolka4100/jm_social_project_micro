package org.javamentor.social.email_service.rest_controllers;

import org.javamentor.social.email_service.model.RequestWrapper;
import org.javamentor.social.email_service.model.dto.MailDto;
import org.javamentor.social.email_service.service.EmailService;
import org.javamentor.social.email_service.service.LoginServiceFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email-service")
public class EmailServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceController.class);

    private final EmailService emailService;
    private final LoginServiceFeignClient loginService;

   @Autowired
   public EmailServiceController(EmailService emailService, LoginServiceFeignClient loginService) {
       this.emailService = emailService;
       this.loginService = loginService;
   }

   @PostMapping("/send-greeting-mail")
   public void sendEmail(@RequestBody String email){
       emailService.sendGreetingHtmlEmail(email);
   }

   @PostMapping("/send-mail")
   public void sendEmail(@RequestBody MailDto mailDto,
//                         @RequestHeader Map<String, String> headers) {
                         @RequestHeader("user_id") String userId){
       try {
           emailService.sendEmail(loginService.getEmailByAccountId(Long.parseLong(userId)), mailDto.getSubject(), mailDto.getText());
       } catch (NumberFormatException nfe) {  // TODO: протестировать
           LOGGER.error("Не удалось извлечь user_id", nfe);
       }
   }

   @PostMapping("/send-greeting-html-mail")
   public void sendGreetingHtmlEmail(@RequestBody String email){
       emailService.sendGreetingHtmlEmail(email);
   }

   @PostMapping("/send-registration-notification")
   public void sendRegistrationNotification(@RequestBody RequestWrapper wrapper){
       emailService.sendRegistrationNotification(wrapper.getAccountDto().getEmail(),
               wrapper.getProfile().getFirstName(),
               wrapper.getProfile().getLastName());
   }

   @PostMapping("/send-payment-notification")
   public void sendPaymentNotification(@RequestBody RequestWrapper wrapper){
       emailService.sendPaymentNotification(wrapper.getAccountDto().getEmail(),
               wrapper.getLastDays(),
               wrapper.getProfile().getFirstName(),
               wrapper.getProfile().getLastName());
   }
}


