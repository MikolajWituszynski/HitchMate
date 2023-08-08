package com.example.HitchMate.controller;

import com.example.HitchMate.entity.Photo;
import com.example.HitchMate.entity.User;
import com.example.HitchMate.requests.PhotoRequest;
import com.example.HitchMate.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class PhotoController {
    @Autowired
    private PhotoService photoService;



    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable Long id, PhotoRequest photoRequest){
            Photo photo = photoService.getPhotoById(id);
            return ResponseEntity.ok(photo);
    }

    @PostMapping("/photos")
    public ResponseEntity<Photo> createPhoto(@RequestBody PhotoRequest photoRequest) {
        Photo photo = photoService.createPhotoFromRequest(photoRequest);
        return ResponseEntity.ok(photo);
    }

    @PutMapping("/photos/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable Long id, @RequestBody PhotoRequest photoRequest, User user) {
        Photo photo = photoService.updatePhotoFromRequest(id,photoRequest, user);
        return ResponseEntity.ok(photo);
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<Photo> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
