package com.jqbss.wordreminder.service.implementation;

import com.jqbss.wordreminder.model.*;
import com.jqbss.wordreminder.reposiotory.AnswerRepository;
import com.jqbss.wordreminder.reposiotory.QuestionRepository;
import com.jqbss.wordreminder.reposiotory.QuizRepository;
import com.jqbss.wordreminder.reposiotory.UserWordRepository;
import com.jqbss.wordreminder.service.QuizService;
import com.jqbss.wordreminder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepository;
    UserService userService;
    UserWordRepository userWordRepository;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;

    public QuizServiceImpl(QuizRepository quizRepository, UserService userService, UserWordRepository userWordRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.userService = userService;
        this.userWordRepository = userWordRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {

        List<UserWord> userWords = userWordRepository.findByUser(getUser());
        LinkedList<Question> questions;
        LinkedList<Answer> answers;

        if(userWords.size()<10)
        {
            quiz.setNumberOfQuestions(userWords.size());
        }
        else{
            quiz.setNumberOfQuestions(10);
        }

        questions = getRandomQuestions(quiz.getNumberOfQuestions(), userWords, quiz);
        answers = declareAnswerList(quiz.getNumberOfQuestions(), quiz);

        quiz.setCurrentNumberOfQuestion(1);
        quiz.setUser(getUser());
        quiz.setQuestions(questions);
        quiz.setAnswers(answers);
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuiz(long id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    private LinkedList<Question> getRandomQuestions(int numberOfQuestions, List<UserWord> userWords, Quiz quiz)
    {
        LinkedList<Question> questions = new LinkedList<>();
        Random rand = new Random();
        for(int i=0; i<numberOfQuestions;i++){
            int randomIndex = rand.nextInt(userWords.size());
            Question question = new Question();
            question.setEnglishName(userWords.get(randomIndex).getEnglishName());
            question.setPolishName(userWords.get(randomIndex).getPolishName());
            question.setQuiz(quiz);
            questions.add(question);
            userWords.remove(randomIndex);
        }
        return questions;
    }
    private LinkedList<Answer> declareAnswerList(int numberOfQuestions, Quiz quiz){
        LinkedList<Answer> answers = new LinkedList<>();
        for(int i=0; i<numberOfQuestions;i++){
            Answer answer = new Answer();
            answer.setQuiz(quiz);
            answers.add(answer);
        }
        return answers;
    }

    private User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUserLogin(auth.getName());
    }
}
