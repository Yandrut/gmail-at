package org.yandrut.gmail_at.model;

public record User(String email, String password) {

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}