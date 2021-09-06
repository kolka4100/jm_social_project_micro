package org.javamentor.social.login.demo.aspects;

import java.lang.annotation.*;
import java.lang.reflect.Type;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckAccount {
    Class object();
}
