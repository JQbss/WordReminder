package com.jqbss.wordreminder;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.reposiotory.UserRepository;
import com.jqbss.wordreminder.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserUnitTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getUsersTest() {
        when(userRepository.findAll()).thenReturn(Stream.
                of(new User(123L, "test", "test@test.pl", "qwe123")).collect(Collectors.toList()));

        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    public void getUser() {
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        String address = "test@test.pl";
        when(userRepository.findByUserEmail(address)).thenReturn(user);

        assertEquals(user, userService.findByUserEmail(address));
        when(userRepository.findById(123L)).thenReturn(java.util.Optional.of(user));
        assertEquals(user,userService.getUser(123L));
        when(userRepository.findByUserLogin("test")).thenReturn(user);
        assertEquals(user,userService.findByUserLogin("test"));

    }

    @Test
    public void saveUserTest() {
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.addUser(user));
    }

    @Test
    public void updateUserTest(){
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.updateUser(user));
    }

    @Test
    public void deleteUserTest() {
        User user = new User(123L, "test", "test@test.pl", "qwe123");
        userService.deleteUser(123L);
        verify(userRepository, times(1)).deleteById(123L);
    }

    @Test
    public void setUserTest(){
        User user = new User();

        long id = 123L;
        String login = "Test";
        String email = "test@test.pl";
        String password = "qwe123!@#";
        String passwordConfirm = "qwe123!@#";

        user.setUserId(id);
        user.setUserLogin(login);
        user.setUserEmail(email);
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);


        assertEquals(login,user.getUserLogin());
        assertEquals(email,user.getUserEmail());
        assertEquals(password,user.getPassword());
        assertEquals(passwordConfirm, user.getPasswordConfirm());
        assertEquals(id,user.getUserId());

    }
}
