package com.jqbss.wordreminder.controller;

import com.jqbss.wordreminder.model.*;
import com.jqbss.wordreminder.reposiotory.AnswerRepository;
import com.jqbss.wordreminder.reposiotory.QuestionRepository;
import com.jqbss.wordreminder.reposiotory.UserRepository;
import com.jqbss.wordreminder.service.QuizService;
import com.jqbss.wordreminder.service.UserService;
import com.jqbss.wordreminder.service.UserWordService;
import com.jqbss.wordreminder.validation.UserWordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LearnController {

    UserWordService userWordService;
    UserWordValidator userWordValidator;
    UserService userService;
    QuizService quizService;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;

    public LearnController(UserWordService userWordService, UserWordValidator userWordValidator,
                           UserService userService, QuizService quizService, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.userWordService = userWordService;
        this.userWordValidator = userWordValidator;
        this.userService = userService;
        this.quizService = quizService;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @GetMapping("/learn")
    public String Learn(){
        return "learn";
    }

    @GetMapping("/addword")
    public String AddWord(UserWord userWord){
        return "addword";
    }

    @PostMapping("/addword")
    public String AddWord(@Valid UserWord userWord, BindingResult bindingResult){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());
        userWord.setUser(user);
        userWordValidator.validate(userWord,bindingResult);
        if(bindingResult.hasErrors()){
            return "addword";
        }
        userWordService.addUserWord(userWord);
        return "redirect:/learn";
    }

    @GetMapping("/allwords")
    public String AllWords(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());
        model.addAttribute("words", userWordService.getAllUserWordsByUser(user));
        return "allwords";
    }

    @GetMapping("/quiz")
    public ModelAndView QuizController(){
        ModelAndView mv = new ModelAndView();

        Quiz quiz = quizService.addQuiz(new Quiz());
        questionRepository.saveAll(quiz.getQuestions());
        answerRepository.saveAll(quiz.getAnswers());
        quiz.setCurrentQuestion(quiz.getQuestions().get(quiz.getCurrentNumberOfQuestion()-1).getEnglishName());
        mv.setViewName("quiz");
        mv.addObject(quiz);
        return mv;
    }

    @PostMapping("/quiz/{id}")
    public ModelAndView QuizControllerPost(@PathVariable("id") long id, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        Quiz quiz = quizService.getQuiz(id);

        List<Answer> answers = answerRepository.findByQuiz(quiz);
        List<Question> questions = questionRepository.findByQuiz(quiz);

        Answer answer = answers.get(quiz.getCurrentNumberOfQuestion()-1);
        Question question = questions.get(quiz.getCurrentNumberOfQuestion()-1);

        answer.setPolishName(request.getParameter("currentAnswer"));

        if(answer.getPolishName().equals(question.getPolishName()))
        {
            answer.setCorrect(true);
        }
        else {
            answer.setCorrect(false);
        }

        answers.set(quiz.getCurrentNumberOfQuestion()-1,answer);
        answerRepository.save(answer);

        quiz.setCurrentNumberOfQuestion(quiz.getCurrentNumberOfQuestion()+1);
        if(quiz.getCurrentNumberOfQuestion()<=quiz.getNumberOfQuestions())
        {
            quiz.setCurrentQuestion(quiz.getQuestions().get(quiz.getCurrentNumberOfQuestion()-1).getEnglishName());

            quiz.getAnswers().get(quiz.getCurrentNumberOfQuestion()-1).setPolishName(request.getParameter("currentAnswer"));
            quizService.updateQuiz(quiz);
            mv.setViewName("quiz");
            mv.addObject(quiz);
        }
        else
        {
           return new ModelAndView("redirect:/summary/"+id);
        }
        return mv;
    }
    @GetMapping("/summary/{id}")
    public ModelAndView QuizSummary(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView();

        Quiz quiz = quizService.getQuiz(id);
        mv.addObject(quiz);
        mv.setViewName("summary");
        return mv;
    }
}
