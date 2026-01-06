package fr.epita.quiz.tests;


import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.exceptions.PersistenceException;
import fr.epita.quiz.services.api.QuestionRepository;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
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

    @Autowired
    QuestionRepository jpaQuestionRepository;

    @Test
    public void testCreate_nominal() throws PersistenceException {

        //given
        Question question = new Question("What is JPA?");

        //when
        jpaQuestionRepository.create(question);

        //then
        System.out.println(question.getId());

    }

}
