package com.reservo.Models;

import jakarta.persistence.*;

@Entity
public class ReservoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_creation")
    private String date_of_creation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
