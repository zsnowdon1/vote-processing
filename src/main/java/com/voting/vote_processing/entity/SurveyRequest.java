package com.voting.vote_processing.entity;

import java.util.Arrays;

public class SurveyRequest {

    private String username;

    private String surveyId;

    private SelectedChoice[] responses;

    public SurveyRequest(String username, String surveyId, SelectedChoice[] responses) {
        this.username = username;
        this.surveyId = surveyId;
        this.responses = responses;
    }

    public SurveyRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public SelectedChoice[] getResponses() {
        return responses;
    }

    public void setResponses(SelectedChoice[] choices) {
        this.responses = choices;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "username='" + username + '\'' +
                ", surveyId=" + surveyId +
                ", choices=" + Arrays.toString(responses) +
                '}';
    }
}
