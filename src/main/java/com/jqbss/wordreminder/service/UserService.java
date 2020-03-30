package com.jqbss.wordreminder.service;

import com.jqbss.wordreminder.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    User getUser(long id);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(long id);
    User findByUserLogin(String login);
    User findByUserEmail(String email);
    User getCurrentLoggedUser();
}
