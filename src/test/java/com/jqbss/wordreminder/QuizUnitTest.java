package com.jqbss.wordreminder;

import com.jqbss.wordreminder.model.Answer;
import com.jqbss.wordreminder.model.Question;
import com.jqbss.wordreminder.model.Quiz;
import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.reposiotory.QuizRepository;
import com.jqbss.wordreminder.service.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
public class QuizUnitTest {

    @Autowired
    private QuizService quizService;

    @MockBean
    private QuizRepository quizRepository;

    private static LinkedList<Question> questionList = new LinkedList<>();
    private LinkedList<Answer> answerList = new LinkedList<>();

    private Quiz quiz = new Quiz();

    @Test
    public static LinkedList<Question> setQuestionTest(){
        Question question = new Question();
        int id=1;
        String polishName = "angielski";
        String englishName = "English";
        question.setQuestionId(id);
        question.setPolishName(polishName);
        question.setEnglishName(englishName);

        assertEquals(id,question.getQuestionId());
        assertEquals(polishName, question.getPolishName());
        assertEquals(englishName, question.getEnglishName());

        questionList.add(question);
        return questionList;
    }

    @Test
    public LinkedList<Answer> setAnswerTest(){
        Answer answer = new Answer();
        int id = 1;
        String polishName = "angielski";

        answer.setAnswerId(id);
        answer.setPolishName(polishName);
        answer.setCorrect(true);

        assertEquals(id, answer.getAnswerId());
        assertEquals(polishName, answer.getPolishName());
        assertTrue(answer.isCorrect());
        answerList.add(answer);
        return answerList;
    }

    @Test
    void setQuizTest(){

        User user = new User(123L, "test", "test@test.pl", "qwe123");

        long id = 111;
        int numberOfQuestions = 0;
        String currentAnswer = "angielski";
        String currentQuestion = "English";
        int currentNumberOfQuestion = 0;


        quiz.setUser(user);
        quiz.setQuizId(id);
        quiz.setAnswers(setAnswerTest());
        for (Answer answer: answerList) {
            answer.setQuiz(quiz);
            assertEquals(quiz,answer.getQuiz());
        }
        quiz.setQuestions(setQuestionTest());
        for(Question question: questionList){
            question.setQuiz(quiz);
            assertEquals(quiz,question.getQuiz());
            numberOfQuestions++;
        }
        quiz.setNumberOfQuestions(numberOfQuestions);
        quiz.setCurrentAnswer("angielski");
        quiz.setCurrentNumberOfQuestion(0);
        quiz.setCurrentQuestion("English");

        assertEquals(id, quiz.getQuizId());
        assertEquals(answerList, quiz.getAnswers());
        assertEquals(questionList, quiz.getQuestions());
        assertEquals(1,quiz.getNumberOfQuestions());
        assertEquals(currentAnswer, quiz.getCurrentAnswer());
        assertEquals(currentQuestion,quiz.getCurrentQuestion());
        assertEquals(currentNumberOfQuestion,quiz.getCurrentNumberOfQuestion());
        assertEquals(user,quiz.getUser());
    }

    @Test
    public void getQuiz() {
        Quiz quiz = new Quiz();


        quiz.setQuestions(questionList);
        long id = 10;
        when(quizRepository.findById(id)).thenReturn(java.util.Optional.of(quiz));
        assertEquals(quiz, quizService.getQuiz(id));
    }
}
