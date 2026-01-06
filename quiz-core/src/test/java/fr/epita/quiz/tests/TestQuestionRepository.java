package fr.epita.quiz.tests;


import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.exceptions.PersistenceException;
import fr.epita.quiz.services.api.QuestionRepository;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = fr.epita.quiz.tests.TestConfig.class)
@Transactional
@Commit
public class TestQuestionRepository {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    @Qualifier("services.data.datasource")
    DataSource dataSource;


    @Test
    @Transactional
    public void testQuestionRepository_create_nominal() throws SQLException, PersistenceException {
        //given
        Question question = new Question("Test");

        //when
        questionRepository.create(question);
        sessionFactory.getCurrentSession().flush(); // Force Hibernate to write to DB

        //then - use Hibernate's connection directly
        sessionFactory.getCurrentSession().doWork(connection -> {
            ResultSet resultSet = connection.prepareStatement("SELECT title FROM question").executeQuery();
            resultSet.next();
            Assertions.assertEquals(question.getTitle(), resultSet.getString("title"));
        });
    }

    @Test
    public void testQuestionRepository_findByTitle_nominal() throws SQLException, PersistenceException {
        //given
        Question question = new Question("Test");
        questionRepository.create(question);
        sessionFactory.getCurrentSession().flush(); // Force Hibernate to write to DB

        //when
        questionRepository.findByTitle("Tes");

        //then - use Hibernate's connection directly
        sessionFactory.getCurrentSession().doWork(connection -> {
            ResultSet resultSet = connection.prepareStatement("SELECT title FROM question").executeQuery();
            resultSet.next();
            Assertions.assertEquals(question.getTitle(), resultSet.getString("title"));
        });
    }
    @Test
    public void testQuestionRepository_findById() throws SQLException, PersistenceException {
        //given
        Question question = new Question("Test");
        questionRepository.create(question);
        sessionFactory.getCurrentSession().flush(); // Force Hibernate to write to DB

        //when
        Question foundQuestion = questionRepository.findById(Question.class, question.getId());


        Assertions.assertNotNull(foundQuestion);
    }

}
