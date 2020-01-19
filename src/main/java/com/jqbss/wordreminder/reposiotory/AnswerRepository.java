package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
