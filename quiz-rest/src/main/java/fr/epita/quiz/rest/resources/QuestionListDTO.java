package fr.epita.quiz.rest.resources;

import java.util.List;

public class QuestionListDTO {


    List<QuestionDTO> questionDTOList;

    public QuestionListDTO(){
    }


    public List<QuestionDTO> getQuestionDTOList() {
        return questionDTOList;
    }

    public void setQuestionDTOList(List<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }
}
