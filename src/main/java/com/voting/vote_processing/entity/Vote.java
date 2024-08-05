package com.voting.vote_processing.entity;

public class Vote {

    private int voteId;

    private int choiceId;

    private String username;

    public Vote(int voteId, int choiceId, String username) {
        this.voteId = voteId;
        this.choiceId = choiceId;
        this.username = username;
    }

    public Vote() { }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", choiceId=" + choiceId +
                ", username='" + username + '\'' +
                '}';
    }
}
