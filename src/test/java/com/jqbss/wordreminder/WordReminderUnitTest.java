package com.jqbss.wordreminder;

import com.jqbss.wordreminder.model.Question;
import com.jqbss.wordreminder.model.Quiz;
import com.jqbss.wordreminder.reposiotory.QuizRepository;
import com.jqbss.wordreminder.reposiotory.UserRepository;
import com.jqbss.wordreminder.service.QuizService;
import com.jqbss.wordreminder.service.UserService;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.jqbss.wordreminder.model.User;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class WordReminderUnitTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Tag("UserTests")
    @Test
    public void getUsersTest(){
        when(userRepository.findAll()).thenReturn( Stream.
                of(new User(123L, "test", "test@test.pl","qwe123")).collect(Collectors.toList()));

        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    @Tag("UserTests")
    public void getUserByAddressTest(){
        User user = new User(123L, "test", "test@test.pl","qwe123");
        String address = "test@test.pl";
        when(userRepository.findByUserEmail(address)).thenReturn(user);

        assertEquals(user,userService.findByUserEmail(address));
    }

    @Test
    @Tag("UserTests")
    public void saveUserTest(){
        User user = new User(123L, "test", "test@test.pl","qwe123");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.addUser(user));
    }

    @Test
    @Tag("UserTests")
    public void deleteUserTest(){
        User user = new User(123L, "test", "test@test.pl","qwe123");
        userService.deleteUser(123L);
        verify(userRepository,times(1)).deleteById(123L);
    }
    
    public class QuizClassTest{
        @Autowired
        private QuizService quizService;

        @MockBean
        private QuizRepository quizRepository;

        @Test
        public void getQuiz(){
            Quiz quiz = new Quiz();

            LinkedList<Question> questions = new LinkedList<>();
            Question question = new Question();
            question.setEnglishName("englishName");
            question.setPolishName("polishName");
            question.setQuestionId(12);
            questions.add(question);

            quiz.setQuestions(questions);
            long id = 10;
            when(quizRepository.findById(id)).thenReturn(java.util.Optional.of(quiz));
            assertEquals(quiz,quizService.getQuiz(id));
        }
    }

}
