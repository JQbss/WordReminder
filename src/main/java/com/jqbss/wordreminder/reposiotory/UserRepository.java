package com.jqbss.wordreminder.reposiotory;

import com.jqbss.wordreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserEmail(String email);
}
