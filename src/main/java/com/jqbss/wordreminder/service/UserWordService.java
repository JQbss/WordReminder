package com.jqbss.wordreminder.service;

import com.jqbss.wordreminder.model.UserWord;

import java.util.List;

public interface UserWordService {

    UserWord addUserWord(UserWord userWord);
    void deleteUserWord(long id);
    List<UserWord> getAllUserWord();
    UserWord getUserWord(long id);
}
