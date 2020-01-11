package com.jqbss.wordreminder.service.implementation;

import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.reposiotory.UserWordRepository;
import com.jqbss.wordreminder.service.UserWordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWordServiceImpl implements UserWordService {

    UserWordRepository userWordRepository;

    public UserWordServiceImpl(UserWordRepository userWordRepository) {
        this.userWordRepository = userWordRepository;
    }

    @Override
    public UserWord addUserWord(UserWord userWord) {
        return userWordRepository.save(userWord);
    }

    @Override
    public void deleteUserWord(long id) {
        userWordRepository.deleteById(id);
    }

    @Override
    public List<UserWord> getAllUserWord() {
        return userWordRepository.findAll();
    }

    @Override
    public UserWord getUserWord(long id) {
        return userWordRepository.findById(id).orElse(null);
    }

    @Override
    public UserWord getUserWordByEnglishName(String englishName) {
        return userWordRepository.findByEnglishName(englishName);
    }
}
