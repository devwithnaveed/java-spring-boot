package fr.epita.quiz.tests;


import fr.epita.quiz.datamodel.Question;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@Commit
@Transactional
public class TestJPA {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testSessionFactory() {
        Session session = sessionFactory.openSession();
        Question question = new Question("What is JPA?");
        session.persist(question);
        System.out.println(question.getId());

    }

}
