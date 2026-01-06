package fr.epita.quiz.services.api;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.exceptions.PersistenceException;

import java.util.List;

public interface MCQChoiceRepository extends Repository<MCQChoice> {


    List<MCQChoice> findByQuestion(Question question);

}
