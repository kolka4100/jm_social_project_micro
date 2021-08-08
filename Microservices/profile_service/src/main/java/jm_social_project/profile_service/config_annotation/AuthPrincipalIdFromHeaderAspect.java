package jm_social_project.profile_service.config_annotation;

import jm_social_project.profile_service.model.Profile;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class AuthPrincipalIdFromHeaderAspect {

    private final AuthPrincipalFilter authPrincipalFilter;

    @Autowired
    public AuthPrincipalIdFromHeaderAspect(AuthPrincipalFilter authPrincipalFilter) {
        this.authPrincipalFilter = authPrincipalFilter;
    }

    @Pointcut("@annotation(AuthPrincipalIdFromHeader)")
    public void executeIdTransfer() {
    }


    @Before("executeIdTransfer()")
    public void idTransfer(JoinPoint joinPoint) {
        List<Object> signatureArgs = List.of(joinPoint.getArgs());
        //Здесь получилась сильная зависимость от модели
        //Пока не нашел способа от нее избавиться
        //Пробовал через рефлексию - пока не получается
        Profile profile = (Profile) signatureArgs.get(0);
        profile.setAccountId(authPrincipalFilter.getAuthHeader());

    }
}

