package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.Answer;
import com.jqbss.wordreminder.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuiz(Quiz quiz);
}
