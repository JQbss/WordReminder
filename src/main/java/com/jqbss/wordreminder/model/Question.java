package com.jqbss.wordreminder.model;

import javax.persistence.*;

@Entity
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;
    private String englishName;
    private String polishName;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPolishName() {
        return polishName;
    }

    public void setPolishName(String polishName) {
        this.polishName = polishName;
    }
}
