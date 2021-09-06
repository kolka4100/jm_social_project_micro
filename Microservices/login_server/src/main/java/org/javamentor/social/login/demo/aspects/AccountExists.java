package org.javamentor.social.login.demo.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.javamentor.social.login.demo.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Aspect
public class AccountExists extends AbstractDaoServiceImpl<Object> {

    public AccountExists() {
    }

    @Before(value = "@annotation(CheckAccount)")
    public void checkUser(JoinPoint joinPoint) throws RuntimeException {
        Object[] arguments = joinPoint.getArgs();
        for (Object o : arguments) {
            Account account = (Account) o;
            Long idCheck = account.getId();
            if (findById(idCheck) == null) {
                throw new NoSuchUserException("Account с id: " + idCheck + " не существует!");
            }
        }
    }
}

//    @Pointcut("execution(* org.javamentor.social.login.demo.controller.UserDataController.testAccount())")
//    public void before(){
//        System.out.println("asfafaf");
//    };


//    @Before("execution(* before()) && args(id)")
//    @Override
//    public Object findById(Long id) throws RuntimeException {
//        Account o = new Account();
//        System.out.println("Метод работает!");
//        return super.findById(id);
//    }
//}



//    @Before(value = "@within(org.javamentor.social.login.demo.aspects.CheckAccount) || @annotation(org.javamentor.social.login.demo.aspects.CheckAccount)")
//    public void before(JoinPoint joinPoint) throws Throwable {
//        System.out.println("Ну заработай!");
//    }


//    @Before(value = "@within(org.javamentor.social.login.demo.aspects.CheckAccount) || " +
//            "@annotation(org.javamentor.social.login.demo.aspects.CheckAccount) && @args((Long id, JoinPoint joinPoint)")
//    public TestDto findById(Long id, JoinPoint joinPoint) throws NoSuchUserException {
//        return super.findById(id);
//    }

    //    @Pointcut("within(@org.javamentor.social.login.demo.aspects.CheckAccount *)")
//    public void beanAnnotatedWithMonitor() {}
//
//    @Pointcut("execution( * *(..))")
//    public void publicMethod() {}
//
//    @Pointcut("publicMethod() && beanAnnotatedWithMonitor()")
//    public void publicMethodInsideAClassMarkedWithAtMonitor() {}
//
//
//    @Before("execution(publicMethodInsideAClassMarkedWithAtMonitor")
//    public Object classAnnotation(JoinPoint joinPoint, CheckAccount checkAccount) {
//        joinPoint.getSignature().getName();
//        return checkAccount.object();
//    }




//    @Before(value = "@annotation(CheckAccount) | args(checkAccount)", argNames = "joinPoint,checkAccount")      //аннотация к методу!
//    public void checkUser(JoinPoint joinPoint, CheckAccount checkAccount) throws Throwable {
//
//
//
//        Class<T> persistentClass = (Class<T> )
//                ((ParameterizedType)getClass().getGenericSuperclass())
//                        .getActualTypeArguments()[0];
//        Object[] arguments = joinPoint.getArgs();
//        System.out.println(checkAccount.object());
//
//        for (Object o : arguments) {
//            if (o instanceof TestDto) {
//                TestDto testDto = (TestDto) o;
//                Long idCheck = testDto.getId();
////                if (accountService.findById(idCheck) == null) {
////                    throw new NoSuchUserException("Account с id: " + idCheck + " не существует!");
////                }
//            }
//        }
//    }

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final Class<T> persistentClass;
//
//    protected AccountExists() {
//        //Get "T" and assign it to this.entityClass
//        Type genericSuperClass = getClass().getGenericSuperclass();
//
//        ParameterizedType parametrizedType = null;
//        while (parametrizedType == null) {
//            if ((genericSuperClass instanceof ParameterizedType)) {
//                parametrizedType = (ParameterizedType) genericSuperClass;
//            } else {
//                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
//            }
//        }
//
//        persistentClass = (Class<T>) parametrizedType.getActualTypeArguments()[0];
//    }
//
//    public T getByKey(PK key) {
//        try {
//            return entityManager.find(persistentClass, key);
//        } catch (NoResultException ignore) {
//
//        }
//        return null;
//    }
//
//    @Override
//    public Object findById(Serializable id) {
//        return null;
//    }


//    @Before("execution(* org.javamentor.social.login.demo.model.request.TestDto.getId())")
//    public void test() {
//        System.out.println("ПРОВЕРКА ID");
//
//    }


//    @Autowired
//    private AccountService accountService;
//
//    /*
//      Ставим аннотацию @CheckAccount над методом, где надо проверить аргумент метода.
//      Сущность проверяется по ID.
//     */
//    @Before(value = "@annotation(CheckAccount)")      //аннотация к методу!
//    public void checkUser(JoinPoint joinPoint) throws RuntimeException {
//        Object[] arguments = joinPoint.getArgs();
//        for (Object o : arguments) {
//            Account account = (Account) o;
//            Long idCheck = account.getId();
//            if (accountService.findById(idCheck) == null) {
//                throw new NoSuchUserException("Account с id: " + idCheck + " не существует!");
//            }
//        }
//    }




