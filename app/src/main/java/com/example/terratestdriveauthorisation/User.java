package com.example.terratestdriveauthorisation;

public class User {
    private String nickname, email, password, avatarImage;

    public User(){}

    public User(String nickname, String email, String password){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public User(String avatarImage){
        this.avatarImage = avatarImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}
