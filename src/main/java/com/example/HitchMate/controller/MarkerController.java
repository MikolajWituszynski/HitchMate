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

import java.util.List;

@RestController
public class MarkerController {

    @Autowired
    private MarkerService markerService;

    @GetMapping("/markers/{id}")
        public ResponseEntity<Marker> getMarker(@PathVariable Long id){
        Marker marker = markerService.getMarkerById(id);
        return ResponseEntity.ok(marker);
}

    @GetMapping("/markers")
    public ResponseEntity<List<Marker>> getMarkers(){
        List<Marker> markers = markerService.getAllMarkers();
        return ResponseEntity.ok(markers);
    }

    @PostMapping("/addMarker")
        public ResponseEntity<Marker> addMarker(@RequestBody Marker marker) {
        Marker newMarker = markerService.addMarker(marker);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMarker);
    }

    @PutMapping("/markers/{id}")
        public ResponseEntity<Marker> updateMarker(@PathVariable Long id, String title, String description) {
        Marker marker = markerService.updateMarker(id,title,description);
        return ResponseEntity.ok(marker);
    }

    @DeleteMapping("markers/{id}")
        public ResponseEntity<Marker> deleteMarker(@PathVariable Long id) {
        Marker marker = markerService.deleteMarker(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
