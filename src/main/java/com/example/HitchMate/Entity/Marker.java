package com.example.HitchMate.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="markers")
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "latitude")
    private Double lat;
    @Column(name = "longitute")
    private Double lng;

    @Column(name = "description")
    private String description;

    @Column(name="ownBy")
    private String ownBy;

    public Marker(Long id, Double lat, Double lng, String description,String ownBy) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.ownBy = ownBy;


    }

    public Marker() {

    }

    public String getOwner() {
        return ownBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}