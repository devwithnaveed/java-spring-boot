package fr.epita.quiz.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name="MCQCHOICE")
public class MCQChoice {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name="valid")
    private boolean valid;

    @Column(name="position")
    private int position;

    @ManyToOne
    @JoinColumn(name = "question_ref")
    private Question question;

    public MCQChoice(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
