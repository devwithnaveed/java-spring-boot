package fr.epita.quiz.services.api;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.exceptions.PersistenceException;

import java.util.List;

public interface QuestionRepository {


    void create(Question question) throws PersistenceException;

    List<Question> findByTitle(String title) throws PersistenceException;

}
