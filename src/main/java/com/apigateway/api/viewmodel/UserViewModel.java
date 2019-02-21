package com.apigateway.api.viewmodel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserViewModel {

    private String id;

    @NotNull
    private String name;

    @Email
    private  String email;

    @NotNull
    private String password;

    private Date lastloggedOn;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getLoggedOn() {
        return lastloggedOn;
    }

    public void setgetLoggedOn(Date lastloggedOn) {
        this.lastloggedOn = lastloggedOn;
    }



}
