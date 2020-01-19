package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
