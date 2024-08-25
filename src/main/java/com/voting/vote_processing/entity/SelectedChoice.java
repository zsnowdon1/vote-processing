package com.voting.vote_processing.entity;

public class SelectedChoice {

    private long questionId;

    private long choiceId;

    public SelectedChoice() {}

    public SelectedChoice(long questionId, long choiceId) {
        this.questionId = questionId;
        this.choiceId = choiceId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(long choiceId) {
        this.choiceId = choiceId;
    }

}
