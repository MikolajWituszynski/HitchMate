package com.example.HitchMate.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="markers")
public class Marker {

    @Id
    @Column(name="marker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;

    @Column(name = "info")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id" , referencedColumnName = "user_id")
    private User user;






    public Marker(Long id,String title, Double lat, Double lng, String info,User user) {
        this.id = id;
        this.title = title;
        this.lat = lat;
        this.lng = lng;
        this.info = info;
        this.user = user;

    }

    public Marker() {

    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getInfo() {
        return info;
    }


    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}