package com.jqbss.wordreminder;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.reposiotory.UserWordRepository;
import com.jqbss.wordreminder.service.UserWordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

        assertEquals(222L, userWord.getUserWordId());
        assertEquals("Angielski", userWord.getPolishName());
        assertEquals("English", userWord.getEnglishName());
        assertEquals("test", userWord.getUser().getUserLogin());
    }


    @Test
    public void deleteUserWordTest(){
        UserWord userWord = setUserWordTest();
        userWordService.deleteUserWord(222);
        verify(userWordRepository,times(1)).deleteById(userWord.getUserWordId());
    }

    @Test
    public void getAllUserWordsTest(){
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        when(userWordRepository.findByUser(user)).thenReturn(Stream.of(setUserWordTest()).collect(Collectors.toList()));

        assertEquals(1,userWordService.getAllUserWordsByUser(user).size());
    }

    @Test
    public void getUserWordByIdTest(){
        UserWord userWord = setUserWordTest();
        when(userWordRepository.findById(222L)).thenReturn(java.util.Optional.of(userWord));
        assertEquals(userWord,userWordService.getUserWord(222L));
    }
    @Test
    public void getUserWordByEnglishName(){
        UserWord userWord = setUserWordTest();
        when(userWordRepository.findByEnglishName("English")).thenReturn(userWord);
        assertEquals(userWord,userWordService.getUserWordByEnglishName("English"));
    }
    @Test
    public void getUserWordByEnglishNameAndUser(){
        UserWord userWord = setUserWordTest();
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        when(userWordRepository.findByEnglishNameAndUser("English",user)).thenReturn(userWord);
        assertEquals(userWord,userWordService.getUserWordByEnglishNameAndUser("English",user));
    }
}
