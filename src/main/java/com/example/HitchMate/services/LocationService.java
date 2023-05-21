package com.example.HitchMate.services;

import com.example.HitchMate.repository.LocationRepository;
import org.springframework.stereotype.Service;
import com.example.HitchMate.entity.Location;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

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

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
