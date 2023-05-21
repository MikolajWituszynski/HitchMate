package com.example.HitchMate.services;

import com.example.HitchMate.entity.Comment;
import com.example.HitchMate.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment) {

        // Perform any necessary validation and transformations
        return commentRepository.save(comment);
    }

    public List<Comment> getAllLocations() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment updateComment(Comment comment) {
        // Perform any necessary validation and transformations
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
