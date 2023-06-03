package com.example.HitchMate.controller;

import com.example.HitchMate.dto.LocationRequest;
import com.example.HitchMate.entity.Location;
import com.example.HitchMate.entity.User;
import com.example.HitchMate.security.JwtUtils;
import com.example.HitchMate.services.LocationService;
import com.example.HitchMate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Location> createLocation(@RequestBody LocationRequest locationRequest) {
            Location location = locationService.createLocationFromRequest(locationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }

    @GetMapping("locations/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id){
        Location location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody LocationRequest locationRequest, User user ) {
        Location location = locationService.updateLocationFromRequest(id,locationRequest,user);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("locations/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
