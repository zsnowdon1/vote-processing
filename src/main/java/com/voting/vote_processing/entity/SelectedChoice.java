package com.voting.vote_processing.entity;

public class SelectedChoice {

    private String questionId;

    private String choiceId;

    public SelectedChoice() {}

    public SelectedChoice(String questionId, String choiceId) {
        this.questionId = questionId;
        this.choiceId = choiceId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

}
