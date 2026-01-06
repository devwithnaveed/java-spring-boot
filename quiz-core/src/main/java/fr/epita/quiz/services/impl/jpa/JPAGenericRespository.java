package fr.epita.quiz.services.impl.jpa;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.exceptions.PersistenceException;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;

public class JPAGenericRespository<T> {

    protected final SessionFactory sessionFactory;

    public JPAGenericRespository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(T instance) throws PersistenceException {
        this.sessionFactory.getCurrentSession().persist(instance);
    }

    @Transactional
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
    public void delete(T instance) throws PersistenceException {
        this.sessionFactory.getCurrentSession().remove(instance);
    }
}
