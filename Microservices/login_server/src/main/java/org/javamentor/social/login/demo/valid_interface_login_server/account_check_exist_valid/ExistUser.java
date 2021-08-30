package org.javamentor.social.login.demo.valid_interface_login_server.account_check_exist_valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AccountExistValidator.class)
public @interface ExistUser {
    String message() default "Account does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

