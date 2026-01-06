package fr.epita.quiz.services.impl.jpa;

import fr.epita.quiz.services.api.Repository;
import fr.epita.quiz.services.api.exceptions.PersistenceException;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;

public abstract class JPAGenericRepository<T> implements Repository<T> {

    protected final SessionFactory sessionFactory;

    public JPAGenericRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void create(T instance) throws PersistenceException {
        this.sessionFactory.getCurrentSession().persist(instance);
    }

    @Transactional
    @Override
    public void update(T instance) throws PersistenceException {
        try {
            this.sessionFactory.getCurrentSession().merge(instance);
        }catch (Exception e){
            // TODO LOGGER
            PersistenceException persistenceException = new PersistenceException();
            persistenceException.initCause(persistenceException);
            throw persistenceException;
        }
    }

    @Transactional
    @Override
    public void delete(T instance) throws PersistenceException {
        this.sessionFactory.getCurrentSession().remove(instance);
    }


    @Override
    public T findById(Class<T> expectedType, Object id) throws PersistenceException {
        return sessionFactory.getCurrentSession().find(expectedType, id);
    }

}
