package com.example.HitchMate.controller;

import com.example.HitchMate.requests.CommentRequest;
import com.example.HitchMate.entity.Comment;
import com.example.HitchMate.entity.Location;
import com.example.HitchMate.entity.User;
import com.example.HitchMate.security.JwtUtils;
import com.example.HitchMate.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    public CommentController(CommentService CommentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createLocation(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.createCommentFromRequest(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getLocation(@PathVariable Long id){
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateLocation(@PathVariable Long id, @RequestBody CommentRequest commentRequest, User user ) {
        Comment comment = commentService.updateCommentFromRequest(id,commentRequest);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
