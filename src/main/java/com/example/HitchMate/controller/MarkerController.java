package com.example.HitchMate.controller;

import com.example.HitchMate.entity.Marker;
import com.example.HitchMate.entity.Photo;
import com.example.HitchMate.requests.PhotoRequest;
import com.example.HitchMate.service.MarkerService;
import com.example.HitchMate.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarkerController {

    @Autowired
    private MarkerService markerService;

@GetMapping("/markers/{id}")
    public ResponseEntity<Marker> getMarker(@PathVariable Long id){
    Marker marker = markerService.getMarkerById(id);
    return ResponseEntity.ok(marker);
}

@PostMapping("/create")
    public ResponseEntity<Marker> createMarker(@RequestBody Marker marker) {
        Marker newMarker = markerService.addMarker(marker);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMarker);
    }

}
