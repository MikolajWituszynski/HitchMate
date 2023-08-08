package com.example.HitchMate.service;

import com.example.HitchMate.requests.LocationRequest;
import com.example.HitchMate.entity.User;
import com.example.HitchMate.exceptions.ResourceNotFoundException;
import com.example.HitchMate.repository.LocationRepository;
import com.example.HitchMate.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.HitchMate.entity.Location;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    private UserRepository userRepository;
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(Location location) {
        // Perform any necessary validation and transformations
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public Location updateLocation(Location location) {
        // Perform any necessary validation and transformations
        return locationRepository.save(location);
    }

    public Location updateLocationFromRequest(Long locationId, LocationRequest request, User user) {
        Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new ResourceNotFoundException("Location nchatGPTot found with id: " + locationId));
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setUser(user);
        return locationRepository.save(location);
    }
    public Location createLocationFromRequest(LocationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));

        Location location = new Location();
        location.setLatitude((int) request.getLatitude());
        location.setLongitude((int) request.getLongitude());
        location.setUser(user);

        return location;
    }
    public void deleteLocation(Long id) {
        if(locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Location id: " +id);
        }
    }

}
