package com.springBoot.Template.Model;

import jakarta.persistence.*;
import java.util.Date;

// LogOut Model
@Entity
@Table(name = "logout")
public class LogOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    @Column(unique=true, length = 5000)
    private String token;

    private Date createdOn;


    // Getter and Setter For Private Variables
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}

