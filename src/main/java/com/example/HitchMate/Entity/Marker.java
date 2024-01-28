package com.example.HitchMate.Entity;

import jakarta.persistence.*;


@Entity
@Table(name="marker")
public class Marker {

    @Id
    @Column(name = "marker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private long lat;
    private long lng;
    private String info;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getLat() {
        return lat;
    }

    public long getLng() {
        return lng;
    }

    public String getInfo() {
        return info;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
