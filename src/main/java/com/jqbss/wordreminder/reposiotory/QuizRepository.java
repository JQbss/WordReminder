package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
