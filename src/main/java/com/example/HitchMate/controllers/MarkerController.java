package com.example.HitchMate.controllers;

import com.example.HitchMate.Entity.Marker;
import com.example.HitchMate.repositories.MarkerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/markers")
public class MarkerController {
        private MarkerRepository markerRepository;

        public MarkerController(MarkerRepository markerRepository) {
            this.markerRepository = markerRepository;
        }
    @GetMapping("/{requestedId}")
    ResponseEntity<Marker> findById(@PathVariable Long requestedId, Principal principal) {
        Marker marker = markerRepository.findByIdAndOwnBy(requestedId, principal.getName());
        if(marker != null) {
            return ResponseEntity.ok(marker);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<Marker>> findAll() {
            List<Marker> allMarkers = markerRepository.findAll();
            return ResponseEntity.ok(allMarkers);
    }

    @PostMapping
    ResponseEntity<Void> createMarker(@RequestBody Marker newMarker, UriComponentsBuilder ucb) {
        Marker savedMarker = markerRepository.save(newMarker);
        URI locationOfNewMarker = ucb
                .path("markers/{id}")
                .buildAndExpand(savedMarker.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewMarker).build();
    }

}
