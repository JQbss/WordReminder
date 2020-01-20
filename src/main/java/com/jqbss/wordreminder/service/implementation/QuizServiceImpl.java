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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());

        List<UserWord> userWords = userWordRepository.findByUser(user);
        LinkedList<Question> questions = new LinkedList<>();
        if(userWords.size()<10)
        {
            quiz.setNumberOfQuestions(userWords.size());
            for (UserWord word: userWords) {
                Question question = new Question();
                question.setEnglishName(word.getEnglishName());
                question.setPolishName(word.getPolishName());
                question.setQuiz(quiz);
                questions.add(question);
            }
        }
        else{
            quiz.setNumberOfQuestions(10);
            Random rand = new Random();
            for(int i=0; i<quiz.getNumberOfQuestions();i++){
                int randomIndex = rand.nextInt(userWords.size());
                Question question = new Question();
                question.setEnglishName(userWords.get(randomIndex).getEnglishName());
                question.setPolishName(userWords.get(randomIndex).getPolishName());
                question.setQuiz(quiz);
                questions.add(question);
                userWords.remove(randomIndex);
            }
        }

        quiz.setCurrentNumberOfQuestion(1);
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
