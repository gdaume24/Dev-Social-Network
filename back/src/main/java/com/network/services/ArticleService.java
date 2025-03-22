package com.network.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.network.dto.CommentDto;
import com.network.models.Article;
import com.network.models.Comment;
import com.network.models.User;
import com.network.payload.request.ArticleRequest;
import com.network.repository.ArticleRepository;
import com.network.repository.CommentRepository;
import com.network.repository.ThemeRepository;
import com.network.repository.UserRepository;

@Service
public class ArticleService {
    
    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private ThemeRepository themeRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, CommentRepository commentRepository, ThemeRepository themeRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.themeRepository = themeRepository;
    }    

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article createArticle(Long userId, ArticleRequest articleRequest) {
        
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        // Ajoute le nom de l'auteur
        User user = userRepository.findById(userId).orElseThrow();
        article.setAuthor(user.getUserName());
        article.setContent(articleRequest.getContent());
        article.setDate(new Timestamp(System.currentTimeMillis()));
        article.setUser(user);
        article.setTheme(themeRepository.findByName(articleRequest.getTheme()));
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
