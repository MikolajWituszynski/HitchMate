package com.example.HitchMate.dto;

import com.example.HitchMate.entity.Location;
import com.example.HitchMate.entity.User;

import java.time.LocalDateTime;

public class PhotoRequest {

    private Long id;
    private Long userId;
    private LocalDateTime createdAt;
    private Location location;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
