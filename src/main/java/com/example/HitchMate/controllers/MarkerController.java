package com.example.HitchMate.controllers;

import com.example.HitchMate.Entities.Marker;
import com.example.HitchMate.Repositories.MarkerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/markers")
public class MarkerController {
        private MarkerRepository markerRepository;
    @GetMapping("/{requestedId}")
    ResponseEntity<Marker> findById(@PathVariable Long requestedId) {
       Optional<Marker> markerOptional = markerRepository.findById(requestedId);
       if(markerOptional.isPresent()) {
           Marker marker = markerOptional.get();
           return ResponseEntity.ok(marker);
       } else {
           return ResponseEntity.notFound().build();
       }

    }

}
