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
    private Long userId;
    private String userLogin;
    private String userEmail;
    private String password;
    @ManyToMany
    private Set<Role> roles;
    @OneToMany
    private Set<UserWord> userWords;

    @Transient
    private String passwordConfirm;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
