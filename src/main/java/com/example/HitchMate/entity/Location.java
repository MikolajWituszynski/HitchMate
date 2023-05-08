package com.example.HitchMate.entity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="locations")
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private int latitude;
    @Column(nullable = false)
    private int longitude;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public Location(Long id, int latitude, int longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {

    }

    public Long getId() {
        return id;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

}
