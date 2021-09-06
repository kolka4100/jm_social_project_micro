package org.javamentor.social.login.demo.aspects;



import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;


public abstract class AbstractDaoServiceImpl<T> implements AbstractDaoService<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> persistentClass;

    public AbstractDaoServiceImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T findById(Long id) {
        try {
            return this.entityManager.find(persistentClass, id);
        } catch (NoResultException e) {
            System.out.println("Сработало!");
            e.getMessage();
        }
        return null;
    }
}

