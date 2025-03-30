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

import com.network.dto.CommentDto;
import com.network.mapper.ArticleMapper;
import com.network.mapper.CommentMapper;
import com.network.models.Article;
import com.network.payload.request.ArticleRequest;
import com.network.payload.request.CommentRequest;
import com.network.services.ArticleService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/articles")
public class ArticleController {

    private ArticleMapper articleMapper;
    private ArticleService articleService;
    private CommentMapper commentMapper;

    public ArticleController(ArticleMapper articleMapper, ArticleService articleService, CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
        this.articleMapper = articleMapper;
        this.articleService = articleService;
    }

    @GetMapping("/subscribed")
    public ResponseEntity<?> getSubscribedArticles() {
        List<Article> articleList  = articleService.getSubscribedArticles();
        return ResponseEntity.ok().body(articleList.stream()
                .map(articleMapper::toDto)
                .toList());
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createArticle(@PathVariable String userId, @Valid @RequestBody ArticleRequest articleRequest) {
        Article article = articleService.createArticle(Long.parseLong(userId), articleRequest);
        return ResponseEntity.ok().body(this.articleMapper.toDto(article));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable("articleId") String id) {
        return ResponseEntity.ok().body(this.articleMapper.toDto(articleService.getArticleById(Long.parseLong(id))));
    }

    @PostMapping("/{articleId}/comment")
    public ResponseEntity<?> commentOnArticle(@PathVariable("articleId") Long articleId, @Valid @RequestBody CommentRequest commentRequest) {
        articleService.addCommentToArticle(articleId, commentRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Comment added successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<?> getCommentsByArticleId(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.ok().body(articleService.getCommentsByArticleId(articleId).stream()
                .map(commentMapper::toDto)
                .toList());
}
}