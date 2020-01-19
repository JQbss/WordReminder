package com.jqbss.wordreminder.service.implementation;

import com.jqbss.wordreminder.model.Question;
import com.jqbss.wordreminder.model.Quiz;
import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.model.UserWord;
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
        quiz.setNumberOfQuestions(20);
        quiz.setCurrentNumberOfQuestion(1);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());
        List<UserWord> userWords = userWordRepository.findByUser(user);
        LinkedList<Question> questions = new LinkedList<>();
        for (UserWord word: userWords) {
            Question question = new Question();
            question.setEnglishName(word.getEnglishName());
            question.setPolishName(word.getPolishName());
            question.setQuiz(quiz);
            questions.add(question);
        }
        quiz.setUser(user);
        quiz.setQuestions(questions);
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
}
