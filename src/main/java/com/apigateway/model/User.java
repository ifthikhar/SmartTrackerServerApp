package com.apigateway.model;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Document(collection = "User")
public class User {
    @Id
    private UUID id;


    private String firstName;


    private String lastName;


    private String email;


    private String password;



    private Date lastLogin;
    public User() {
        this.id = UUID.randomUUID();
        this.lastLogin = new Date();
    }


    public User(String firstName,String lastName,  String email, String password) {
        this();
        this.firstName = firstName;
        this.lastName= lastName;
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




    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
