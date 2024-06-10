package com.dev.quiz.Model;

public class MessageDetails {

    private int ID;
    private String message;
    private String details ;

    public MessageDetails(int ID, String message, String details) {
        this.ID = ID;
        this.message = message;
        this.details = details;
    }

    /*----------------------*/

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
