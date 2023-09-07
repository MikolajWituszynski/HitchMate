package com.example.HitchMate.Entities;
import org.springframework.data.annotation.Id;

public record Marker(Long id, Long lat, Long lng, String owner) {

}
