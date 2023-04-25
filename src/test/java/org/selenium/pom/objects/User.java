package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class User {
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

//    public User(String username, String password) throws IOException {
//        User [] users = JacksonUtils.deserializeJson("users.json", User[].class);
//
//        for(User user: users){
//            if(user.getUsername() == username){
//                this.password = user.getPassword();
//                this.username = username;
//            }
//        }
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
