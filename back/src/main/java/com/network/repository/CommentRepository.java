package com.network.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.network.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
   List<Comment> findByArticleId(Long articleId);
}
