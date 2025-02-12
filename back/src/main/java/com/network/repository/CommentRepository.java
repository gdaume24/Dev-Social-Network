package com.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.network.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
