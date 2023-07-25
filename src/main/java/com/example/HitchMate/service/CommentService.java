package com.example.HitchMate.service;

import com.example.HitchMate.dto.CommentRequest;
import com.example.HitchMate.entity.Comment;
import com.example.HitchMate.exceptions.ResourceNotFoundException;
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


    //request
    public Comment updateCommentFromRequest(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () ->
            new ResourceNotFoundException("Comment id: " + id));
        comment.setContent(commentRequest.getContent());
        comment.setLocation(commentRequest.getLocation());
        return comment;
    }

    public Comment createCommentFromRequest(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setLocation(commentRequest.getLocation());
        comment.setContent(commentRequest.getContent());
        return comment;
    }
}
