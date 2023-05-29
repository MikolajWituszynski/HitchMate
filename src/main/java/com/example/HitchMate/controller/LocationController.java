package com.example.HitchMate.controller;

import com.example.HitchMate.dto.LocationRequest;
import com.example.HitchMate.entity.Location;
import com.example.HitchMate.security.JwtUtils;
import com.example.HitchMate.services.LocationService;
import com.example.HitchMate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<?> createLocation(@RequestBody LocationRequest locationRequest) {
            Location location = locationService.createLocationFromRequest(locationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(location);

    }
    
}
