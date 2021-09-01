package org.javamentor.social.login.demo.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.javamentor.social.login.demo.model.Account;
import org.javamentor.social.login.demo.model.dto.TestDto;
import org.javamentor.social.login.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountExists {

    @Autowired
    private AccountService accountService;

    /*
    Ставим аннотацию @CheckAccount над методом, где надо проверить аргумент метода.
    Сущность проверяется по ID.
     */
    @Before(value = "@annotation(CheckAccount)")      //аннотация к методу!
    public void checkUser(JoinPoint joinPoint) throws RuntimeException {
        Object[] arguments = joinPoint.getArgs();
        for (Object o : arguments) {
            if (o instanceof TestDto) {               //проверка входящего аргумента метода на соответсвие TestDto
                TestDto testDto = (TestDto) o;
                Long idTestDtoCheckAccount = testDto.getId();
                if (accountService.findById(idTestDtoCheckAccount) == null) { //проверка id входящего TestDto в базе Account
                    throw new NoSuchUserException("Аккаунт с ID: " + idTestDtoCheckAccount + " отсутствует!");
                }
            } else if (o instanceof Account) { //проверка входящего аргумента метода на соответсвие Account
                Account account = (Account) o;
                Long idAccountCheck = account.getId();
                if (accountService.findById(idAccountCheck) == null) { //проверка id входящего Account в базе Account
                    throw new NoSuchUserException("Аккаунт с ID: " + idAccountCheck + " отсутствует!");
                }
            }
        }
    }
}

