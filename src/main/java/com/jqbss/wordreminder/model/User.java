package com.jqbss.wordreminder.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userLogin;
    private String userEmail;
    private String password;
    @ManyToMany
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    private Set<UserWord> userWords;

    @OneToMany(mappedBy = "user")
    private Set<Quiz> Quizzes;

    public User(Long userId, String userLogin, String userEmail, String password) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.password = password;
    }

    public User(){

    }

    @Transient
    private String passwordConfirm;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<UserWord> getUserWords() {
        return userWords;
    }

    public void setUserWords(Set<UserWord> userWords) {
        this.userWords = userWords;
    }

    public Set<Quiz> getQuizzes() {
        return Quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        Quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
