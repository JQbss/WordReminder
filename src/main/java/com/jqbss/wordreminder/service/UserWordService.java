package com.jqbss.wordreminder.service;

import com.jqbss.wordreminder.model.Question;
import com.jqbss.wordreminder.model.Quiz;
import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.model.User;

import java.util.List;
import java.util.Set;

public interface UserWordService {

    UserWord addUserWord(UserWord userWord);
    void deleteUserWord(long id);
    List<UserWord> getAllUserWordsByUser(User user);
    UserWord getUserWord(long id);
    UserWord getUserWordByEnglishName(String englishName);
    UserWord getUserWordByEnglishNameAndUser(String englishName, User user);
}
