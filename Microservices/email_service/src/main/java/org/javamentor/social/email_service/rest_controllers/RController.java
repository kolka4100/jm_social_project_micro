package org.javamentor.social.email_service.rest_controllers;

import org.javamentor.social.email_service.model.RequestWrapper;
import org.javamentor.social.email_service.model.dto.MailDto;
import org.javamentor.social.email_service.service.EmailService;
import org.javamentor.social.email_service.service.LoginServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class RController {

    private final EmailService emailService;
    private final LoginServiceFeignClient loginService;

    @Autowired
    public RController(EmailService emailService,
                       LoginServiceFeignClient loginService) {
        this.emailService = emailService;
        this.loginService = loginService;
    }

    @PostMapping("/send-mail")
    public Response sendEmail(@RequestBody MailDto mailDto,
                              @RequestHeader("user_id") String userId){
        emailService.sendEmail(loginService.getEmailByAccountId(Long.parseLong(userId)), mailDto.getSubject(), mailDto.getText());
        return Response.ok().build();
    }

    @PostMapping("/send-greeting-html-mail")
    public Response sendGreetingHtmlEmail(@RequestBody String email){
        emailService.sendGreetingHtmlEmail(email);
        return Response.ok().build();
    }

    @PostMapping("/send-registration-notification")
    public Response sendRegistrationNotification(@RequestBody RequestWrapper wrapper){
        emailService.sendRegistrationNotification(wrapper.getAccountDto().getEmail(),
                wrapper.getProfile().getFirstName(),
                wrapper.getProfile().getLastName());
        return Response.ok().build();
    }

    @PostMapping("/send-payment-notification")
    public Response sendPaymentNotification(@RequestBody RequestWrapper wrapper){
        emailService.sendPaymentNotification(wrapper.getAccountDto().getEmail(),
                wrapper.getLastDays(),
                wrapper.getProfile().getFirstName(),
                wrapper.getProfile().getLastName());
        return Response.ok().build();
    }
}
