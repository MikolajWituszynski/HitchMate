package com.example.HitchMate.services;

import com.example.HitchMate.entity.Comment;
import com.example.HitchMate.entity.Photo;
import com.example.HitchMate.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo addPhoto(Photo photo) {

        // Perform any necessary validation and transformations
        return photoRepository.save(photo);
    }

    public List<Photo> getAllLocations() {
        return photoRepository.findAll();
    }

    public Photo getCommentById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo updateComment(Photo photo) {
        // Perform any necessary validation and transformations
        return photoRepository.save(photo);
    }

    public void deleteComment(Long id) {
        photoRepository.deleteById(id);
    }
}
