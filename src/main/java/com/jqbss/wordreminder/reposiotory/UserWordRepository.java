package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    UserWord findByEnglishName(String englishName);
}
