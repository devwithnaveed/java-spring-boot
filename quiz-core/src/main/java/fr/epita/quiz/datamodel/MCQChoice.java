package fr.epita.quiz.datamodel;

import jakarta.persistence.ManyToOne;

public class MCQChoice {

    private String title;
    private boolean valid;
    private int position;

    @ManyToOne
    private Question question;
}
