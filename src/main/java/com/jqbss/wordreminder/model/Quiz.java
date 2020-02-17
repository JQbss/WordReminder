package com.jqbss.wordreminder.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "quiz")
    private List<Question> Questions;
    @OneToMany(mappedBy = "quiz")
    private List<Answer> Answers;


    private int numberOfQuestions;
    private int currentNumberOfQuestion;

    @Transient
    private String currentQuestion;
    @Transient
    private String currentAnswer;


    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        Questions = questions;
    }

    public List<Answer> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<Answer> answers) {
        Answers = answers;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getCurrentNumberOfQuestion() {
        return currentNumberOfQuestion;
    }

    public void setCurrentNumberOfQuestion(int currentNumberOfQuestion) {
        this.currentNumberOfQuestion = currentNumberOfQuestion;
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public void setCurrentAnswer(String currentAnswer) {
        this.currentAnswer = currentAnswer;
    }
    public void addAnswer(Answer answer){
        Answers.add(answer);
    }
}
