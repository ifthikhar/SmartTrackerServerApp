package com.apigateway.model;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Document(collection = "User")
public class User {
    @Id
    private UUID id;


    private String name;


    private String email;


    private String password;



    private Date lastLogin;
    public User() {
        this.id = UUID.randomUUID();
        this.lastLogin = new Date();
    }


    public User(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;

    }
    public UUID getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public Date getLastLogin() {
        return lastLogin;
    }


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}
