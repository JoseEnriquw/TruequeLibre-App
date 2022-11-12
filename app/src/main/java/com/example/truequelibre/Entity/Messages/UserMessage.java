package com.example.truequelibre.Entity.Messages;

import com.example.truequelibre.Entity.Usuario;

import java.sql.Time;

public class UserMessage {
    String message;
    Usuario user;
    long createdAt;

    public UserMessage(String message, Usuario user, long createdAt) {
        this.message = message;
        this.user = user;
        this.createdAt = createdAt;
    }

    public UserMessage() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}


