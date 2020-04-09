package com.jqbss.wordreminder;

import com.jqbss.wordreminder.model.Answer;
import com.jqbss.wordreminder.model.Question;
import com.jqbss.wordreminder.model.Quiz;
import com.jqbss.wordreminder.reposiotory.AnswerRepository;
import com.jqbss.wordreminder.reposiotory.QuestionRepository;
import com.jqbss.wordreminder.reposiotory.QuizRepository;
import com.jqbss.wordreminder.service.QuizService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
public class QuizUnitTest {

    @Autowired
    private QuizService quizService;

    @MockBean
    private QuizRepository quizRepository;

    private List<Question> questionList = new LinkedList<>();
    private List<Answer> answerList = new LinkedList<>();

    private Quiz quiz = new Quiz();

    @Test
    @Before
    public void setQuestionTest(){
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
    }

    @Test
    @Before
    public void setAnswerTest(){
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
    }

    @Test
    void setQuizTest(){

        long id = 111;
        int numberOfQuestions = 0;
        String currentAnswer = "angielski";
        String currentQuestion = "English";
        int currentNumberOfQuestion = 0;


        quiz.setQuizId(id);
        quiz.setAnswers(answerList);
        for (Answer answer: answerList) {
            answer.setQuiz(quiz);
            assertEquals(quiz,answer.getQuiz());
        }
        quiz.setQuestions(questionList);
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
