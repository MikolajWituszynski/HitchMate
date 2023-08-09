package com.example.HitchMate.service;

import com.example.HitchMate.entity.Marker;
import com.example.HitchMate.exceptions.ResourceNotFoundException;
import com.example.HitchMate.repository.MarkerRepository;
import com.example.HitchMate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkerService {
    private final MarkerRepository markerRepository;

    private UserRepository userRepository;
    public MarkerService(MarkerRepository markerRepository) {
        this.markerRepository = markerRepository;
    }

    public Marker addMarker(Marker marker) {
        return markerRepository.save(marker);
    }

    public Marker deleteMarker(Long id) {
        if(markerRepository.existsById(id)) {
            markerRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Marker with id " + id + "does not exits");
        }
        return null;
    }

    public Marker getMarkerById(Long id) {
        return markerRepository.findById(id).orElse(null);

    }

    public Marker updateMarker(Long id, String title, String description) {
        Marker marker = markerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Location not found with id: " + id));
        marker.setTitle(title);
        marker.setDescription(description);
        return markerRepository.save(marker);
    }


    public List<Marker> getAllMarkers() {
        return markerRepository.findAll();
    }


}
