package com.example.HitchMate.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Marker> markers = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public User(Long id, String username, String password, String email, List<Marker> markers, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.markers = markers;
        this.comments = comments;
    }

    public User() {

    }

    public Long getUser_id() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Marker> getMarkers() {
        return markers;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
