package jm_social_project.profile_service.config_annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthPrincipalIdFromHeaderAspect {

    @Pointcut("@annotation(AuthPrincipalIdFromHeader)")
    public void executeIdTransfer() {
    }

    @Before("executeIdTransfer()")
    public void idTransfer(JoinPoint joinPoint) {
        Object[] signatureArgs = joinPoint.getArgs();
        //Логику еще пишу
        //Надо как то вытянуть из хедера айди и засунуть в профиль
    }
}
