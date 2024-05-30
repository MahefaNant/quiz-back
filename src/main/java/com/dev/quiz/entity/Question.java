package com.dev.quiz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type type ;

    @Column(name = "question")
    private String question;

    @Column(name = "isinprogres")
    private boolean isInProgres;

    /*------------------------*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isInProgres() {
        return isInProgres;
    }

    public void setInProgres(boolean inProgres) {
        isInProgres = inProgres;
    }
}
