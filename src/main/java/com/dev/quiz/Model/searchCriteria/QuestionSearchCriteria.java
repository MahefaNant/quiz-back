package com.dev.quiz.Model.searchCriteria;

public class QuestionSearchCriteria {
    private Integer idType;
    private String question;
    private Boolean isInProgress;

    /*-------------------*/

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getIsInProgress() {
        return isInProgress;
    }

    public void setIsInProgress(Boolean inProgress) {
        isInProgress = inProgress;
    }
}
