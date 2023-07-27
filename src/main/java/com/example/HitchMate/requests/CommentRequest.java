package com.example.HitchMate.requests;

import com.example.HitchMate.entity.Location;

public class CommentRequest {

    private String content;
    private Location location;

    public void setContent(String content) {
        this.content = content;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public Location getLocation() {
        return location;
    }
}
