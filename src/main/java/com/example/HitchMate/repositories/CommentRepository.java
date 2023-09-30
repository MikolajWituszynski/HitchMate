package com.example.HitchMate.repositories;

import com.example.HitchMate.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
