package com.example.HitchMate.controllers;

import com.example.HitchMate.Entity.Marker;
import com.example.HitchMate.Repositories.MarkerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/markers")
public class MarkerController {
        private MarkerRepository markerRepository;
    @GetMapping("/{requestedId}")
    ResponseEntity<Marker> findById(@PathVariable Long requestedId, Principal principal) {
      Marker marker = markerRepository.findByUserIdAndOwner(requestedId, principal.getName());
       if(marker != null) {
           return ResponseEntity.ok(marker);
       } else {
           return ResponseEntity.notFound().build();
       }

    }

}
