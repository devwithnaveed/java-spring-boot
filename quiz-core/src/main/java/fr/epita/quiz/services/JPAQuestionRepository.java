package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Question;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class JPAQuestionRepository implements QuestionRepository {


    private final SessionFactory sessionFactory;

    public JPAQuestionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(Question question) throws SQLException {
        this.sessionFactory.getCurrentSession().persist(question);
    }


    @Transactional
    public void update(Question question) throws SQLException {
        this.sessionFactory.getCurrentSession().merge(question);
    }

    @Transactional
    public void delete(Question question) throws SQLException {
        this.sessionFactory.getCurrentSession().remove(question);
    }

    @Override
    public List<Question> findByTitle(String title) throws SQLException {
        Query<Question> query = this.sessionFactory
                .getCurrentSession()
                .createQuery("from Question where title like :param", Question.class);
        query.setParameter("param", title+"%");
        return query.getResultList();
    }
}
