package com.udacity.course3.reviews.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;


public class CommentDoc implements Serializable {

    private String login;

    @NotNull
    private String text;

    public CommentDoc() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
