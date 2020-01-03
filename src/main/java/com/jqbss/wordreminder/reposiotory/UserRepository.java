package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserLogin(String login);
}
