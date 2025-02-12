package com.network.service;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.network.dto.CommentDto;
import com.network.models.Article;
import com.network.models.Comment;
import com.network.models.User;
import com.network.repository.ArticleRepository;
import com.network.repository.CommentRepository;
import com.network.repository.UserRepository;

@Service
public class ArticleService {
    
    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }    

    public Article createArticle(Long userId, Article article) {
        // Ajouter id, date et author
        User user = userRepository.findById(userId).orElseThrow();
        article.setUser(user);
        article.setAuthor(user.getUserName());
        article.setDate(new Timestamp(System.currentTimeMillis()));
        Article savedArticle = articleRepository.save(article);
        return savedArticle;
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }   

    public Comment addCommentToArticle(Long articleId, Long userId, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setArticle(article);
        comment.setUser(user);
        article.setAuthor(user.getUserName());
        article.setDate(new Timestamp(System.currentTimeMillis()));

        return commentRepository.save(comment);
    }
}
