package org.javamentor.social.login.demo.valid_interface_login_server.account_check_exist_valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountExistValidator implements ConstraintValidator<ExistUser, Long> {

    @Autowired
    AccountService accountService;

    @Override
    public void initialize(ExistUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long idUser, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (accountService.findById(idUser) == null) {
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return true;
    }
}

