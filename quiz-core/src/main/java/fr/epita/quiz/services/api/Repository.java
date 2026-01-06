package fr.epita.quiz.services.api;

import fr.epita.quiz.services.api.exceptions.PersistenceException;
import jakarta.transaction.Transactional;

public interface Repository<T> {
    @Transactional
    void create(T instance) throws PersistenceException;

    @Transactional
    void update(T instance) throws PersistenceException;

    @Transactional
    void delete(T instance) throws PersistenceException;

    T findById(Class<T> expectedType, Object id) throws PersistenceException;
}
