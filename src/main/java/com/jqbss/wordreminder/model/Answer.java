package com.jqbss.wordreminder.model;

import javax.persistence.*;

@Entity
public class Answer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;
    private String polishName;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getPolishName() {
        return polishName;
    }

    public void setPolishName(String polishName) {
        this.polishName = polishName;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
