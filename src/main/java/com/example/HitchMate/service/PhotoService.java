package com.example.HitchMate.service;

import com.example.HitchMate.dto.PhotoRequest;
import com.example.HitchMate.entity.Photo;
import com.example.HitchMate.entity.User;
import com.example.HitchMate.exceptions.ResourceNotFoundException;
import com.example.HitchMate.repository.PhotoRepository;
import com.example.HitchMate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private final PhotoRepository photoRepository;
    @Autowired
    private  UserRepository userRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo addPhoto(Photo photo) {

        // Perform any necessary validation and transformations
        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo updatePhoto(Photo photo) {
        // Perform any necessary validation and transformations
        return photoRepository.save(photo);
    }

    //requests

    public Photo createPhotoFromRequest(PhotoRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
            Photo photo = new Photo();
            photo.setCreatedAt(request.getCreatedAt());
            photo.setLocation(request.getLocation());
            photo.setUrl(request.getUrl());
            photo.setUser(user);
            return photo;
        }


    public Photo updatePhotoFromRequest(Long id, PhotoRequest request, User user) {
        Photo photo = photoRepository.findById(id).orElseThrow(() ->
             new ResourceNotFoundException("Photo id: " + id)
        );
        photo.setCreatedAt(request.getCreatedAt());
        photo.setUrl(request.getUrl());
        photo.setUser(user);
        photo.setLocation(request.getLocation());
        return photo;
    }

    public void deletePhoto(Long id) {
        if(photoRepository.existsById(id)) {
            photoRepository.deleteById(id);
        }
    }
}
