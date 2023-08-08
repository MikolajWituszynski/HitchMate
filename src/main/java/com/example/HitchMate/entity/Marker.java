package com.example.HitchMate.entity;

import jakarta.persistence.*;

@Entity
@Table(name="marker")
public class Marker {

    @Id
    private Long id;

    @Column(name="Title", length=20, nullable=false, unique = false)
    private String title;

    @Column(name="Description", length=50, nullable=false, unique = false)
    private String description;

    @Column(name="latitude", length=20, nullable=false, unique = false)
    private float lat;

    @Column(name="longitude", length=20, nullable=false, unique = false)
    private float lng;

    public Marker(Long id, String title, String description, float lat, float lng) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
    }

    public Marker(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;

    }

    public Marker() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
