package com.network.services;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.network.models.Article;
import com.network.models.Comment;
import com.network.models.Theme;
import com.network.models.User;
import com.network.payload.request.ArticleRequest;
import com.network.payload.request.CommentRequest;
import com.network.repository.ArticleRepository;
import com.network.repository.CommentRepository;
import com.network.repository.ThemeRepository;

@Service
public class ArticleService {
    
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;
    private ThemeRepository themeRepository;
    private UserService userService;

    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository, ThemeRepository themeRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.themeRepository = themeRepository;
    }    

    /**
     * Récupère les articles des thèmes auxquels l'utilisateur authentifié est abonné.
     *
     * @return Une liste d'articles triée du plus récent au plus ancien.
     */
    public List<Article> getSubscribedArticles() {
        
        User user = userService.getAuthenticatedUser();

        // Récupère la liste des articles des thèmes auxquels l'utilisateur est abonné
        List<Theme> subscribedThemes = user.getThemes();
        List<Article> filtredArticleList = articleRepository.findByThemeIn(subscribedThemes);

        // Trie les articles du plus récent au plus ancien
        filtredArticleList.sort(Comparator.comparing(Article::getDate).reversed());

        return filtredArticleList;
    }


    public Article createArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());

        // Ajoute le nom de l'auteur
        User user = userService.getAuthenticatedUser();
        article.setUser(user);
        article.setAuthor(user.getUserName());

        article.setContent(articleRequest.getContent());
        article.setDate(LocalDateTime.now());
        article.setTheme(themeRepository.findByName(articleRequest.getTheme()));
        Article savedArticle = articleRepository.save(article);
        
        return savedArticle;
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }   

    public Comment addCommentToArticle(Long articleId, CommentRequest commentRequest) {
        
        Article article = articleRepository.findById(articleId).orElseThrow();
        User user = userService.getAuthenticatedUser();

        Comment comment = new Comment();
        comment.setContent(commentRequest.getComment());
        comment.setArticle(article);
        comment.setUser(user);
        comment.setAuthor(user.getUserName());
        comment.setDate(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}
