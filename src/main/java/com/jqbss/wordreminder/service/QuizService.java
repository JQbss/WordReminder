package com.jqbss.wordreminder.service;

import com.jqbss.wordreminder.model.Quiz;

public interface QuizService {
    Quiz addQuiz(Quiz quiz);
    Quiz getQuiz(long id);
    Quiz updateQuiz(Quiz quiz);
}
