package com.jqbss.wordreminder.service.implementation;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.reposiotory.UserRepository;
import com.jqbss.wordreminder.reposiotory.UserWordRepository;
import com.jqbss.wordreminder.service.UserService;
import com.jqbss.wordreminder.service.UserWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserWordServiceImpl implements UserWordService {

    private UserWordRepository userWordRepository;
    private final UserService userService;

    public UserWordServiceImpl(UserWordRepository userWordRepository, UserService userService) {
        this.userWordRepository = userWordRepository;
        this.userService = userService;
    }

    @Override
    public UserWord addUserWord(UserWord userWord) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());
        user.setUserWords(new HashSet<>(Collections.singletonList(userWord)));
        userService.updateUser(user);
        return userWordRepository.save(userWord);
    }

    @Override
    public void deleteUserWord(long id) {
        userWordRepository.deleteById(id);
    }

    @Override
    public List<UserWord> getAllUserWordsByUser(User user) {
        return userWordRepository.findByUser(user);
    }

    @Override
    public UserWord getUserWord(long id) {
        return userWordRepository.findById(id).orElse(null);
    }

    @Override
    public UserWord getUserWordByEnglishName(String englishName) {
        return userWordRepository.findByEnglishName(englishName);
    }

    @Override
    public UserWord getUserWordByEnglishNameAndUser(String englishName, User user) {
        return userWordRepository.findByEnglishNameAndUser(englishName, user);
    }
}
