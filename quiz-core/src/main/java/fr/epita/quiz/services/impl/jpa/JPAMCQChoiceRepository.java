package fr.epita.quiz.services.impl.jpa;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.MCQChoiceRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class JPAMCQChoiceRepository extends JPAGenericRepository<MCQChoice> implements MCQChoiceRepository {


    public JPAMCQChoiceRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<MCQChoice> findByQuestion(Question question) {
        Query<MCQChoice> query = sessionFactory.getCurrentSession().createQuery("from MCQChoice where question = :question", MCQChoice.class);
        query.setParameter("question", question);
        return query.list();
    }
}
