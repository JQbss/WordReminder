package com.jqbss.wordreminder.model;

import javax.persistence.*;

@Entity
public class UserWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userWordId;
    private String englishName;
    private String polishName;

    @ManyToOne
    @JoinColumn
    private User user;

    public int getUserWordId() {
        return userWordId;
    }

    public void setUserWordId(int userWordId) {
        this.userWordId = userWordId;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPolishName() {
        return polishName;
    }

    public void setPolishName(String polishName) {
        this.polishName = polishName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
