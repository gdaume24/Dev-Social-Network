package com.network.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.dto.ArticleDto;
import com.network.dto.CommentDto;
import com.network.mapper.ArticleMapper;
import com.network.models.Article;
import com.network.payload.request.ArticleRequest;
import com.network.services.ArticleService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/articles")
public class ArticleController {

    private ArticleMapper articleMapper;
    private ArticleService articleService;

    public ArticleController(ArticleMapper articleMapper, ArticleService articleService) {
        this.articleMapper = articleMapper;
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<?> getAllArticles() {
        return ResponseEntity.ok().body(articleService.getAllArticles().stream()
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

    @PostMapping("/{articleId}/comment/{userId}")
    public ResponseEntity<?> commentOnArticle(@PathVariable("articleId") Long articleId, @PathVariable("userId") Long userId, @Valid @RequestBody CommentDto commentDto) {
        articleService.addCommentToArticle(articleId, userId, commentDto);
        return ResponseEntity.ok().body("Comment added successfully");
    }
}
