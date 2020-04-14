package com.jqbss.wordreminder;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.reposiotory.UserWordRepository;
import com.jqbss.wordreminder.service.UserWordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserWordUnitTest {
    @Autowired
    private UserWordService userWordService;

    @MockBean
    private UserWordRepository userWordRepository;

    public static UserWord setUserWordTest(){
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        UserWord userWord = new UserWord();
        userWord.setPolishName("Angielski");
        userWord.setEnglishName("English");
        userWord.setUserWordId(222);
        userWord.setUser(user);

        return userWord;
    }

    @Test
    public void getUserWordTest(){
        UserWord userWord = setUserWordTest();

    }
}
