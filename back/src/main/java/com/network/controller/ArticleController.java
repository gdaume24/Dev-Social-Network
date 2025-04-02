package com.network.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.mapper.ArticleMapper;
import com.network.mapper.CommentMapper;
import com.network.models.Article;
import com.network.payload.request.ArticleRequest;
import com.network.payload.request.CommentRequest;
import com.network.services.ArticleService;

import jakarta.validation.Valid;

/*
 * Controller class for handling article-related requests.
 */
@RestController
@CrossOrigin
@RequestMapping("/articles")
public class ArticleController {

    private ArticleMapper articleMapper;
    private ArticleService articleService;
    private CommentMapper commentMapper;

    /**
     * Constructor for ArticleController.
     *
     * @param articleMapper  Mapper for converting Article entities to DTOs.
     * @param articleService Service for handling article-related operations.
     * @param commentMapper  Mapper for converting Comment entities to DTOs.
     */
    public ArticleController(ArticleMapper articleMapper, ArticleService articleService, CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
        this.articleMapper = articleMapper;
        this.articleService = articleService;
    }

    /*
     * Endpoint to get all articles from the themes the user is subscribed to.
     * 
     * @return A ResponseEntity containing a list of article DTOs.
     */
    @GetMapping("/subscribed")
    public ResponseEntity<?> getSubscribedArticles() {
        List<Article> articleList  = articleService.getSubscribedArticles();
        return ResponseEntity.ok().body(articleList.stream()
                .map(articleMapper::toDto)
                .toList());
    }

    /*
     * Create an article
     * 
     * @param ArticleRequest The request payload containing article details.
     * @return A ResponseEntity containing the created article DTO.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        Article article = articleService.createArticle(articleRequest);
        return ResponseEntity.ok().body(this.articleMapper.toDto(article));
    }

    /*
     * Retrieves an article by its ID.
     * 
     * @param id The ID of the article to retrieve.
     * @return A ResponseEntity containing the article DTO.
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable("articleId") String id) {
        return ResponseEntity.ok().body(this.articleMapper.toDto(articleService.getArticleById(Long.parseLong(id))));
    }

    /*
     * Add a comment to an article.
     * @return A ResponseEntity containing a success message.
     */
    @PostMapping("/{articleId}/comment")
    public ResponseEntity<?> commentOnArticle(@PathVariable("articleId") Long articleId, @Valid @RequestBody CommentRequest commentRequest) {
        articleService.addCommentToArticle(articleId, commentRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Comment added successfully");
        return ResponseEntity.ok().body(response);
    }

    /*
     * Retrieves all comments for a specific article.
     * @return A ResponseEntity containing a list of comment DTOs.
     */
    @GetMapping("/{articleId}/comments")
    public ResponseEntity<?> getCommentsByArticleId(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.ok().body(articleService.getCommentsByArticleId(articleId).stream()
                .map(commentMapper::toDto)
                .toList());
    }
}