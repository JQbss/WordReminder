package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    UserWord findByEnglishName(String englishName);
    UserWord findByEnglishNameAndUser(String englishName,User user);
    List<UserWord> findByUser(User user);
}
