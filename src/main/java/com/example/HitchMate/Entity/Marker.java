package com.example.HitchMate.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="markers")
public class Marker {

    @Id
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
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "marker", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();





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