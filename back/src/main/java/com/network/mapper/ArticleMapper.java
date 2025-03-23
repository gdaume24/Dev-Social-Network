package com.network.mapper;

import org.springframework.stereotype.Component;

import com.network.dto.ArticleDto;
import com.network.models.Article;

@Component
public class ArticleMapper {
    public ArticleDto toDto(Article article) {
        return new ArticleDto(
            article.getId(),
            article.getTitle(),
            article.getAuthor(),
            article.getContent(),
            article.getDate(),
            article.getTheme().getName() // Récupère uniquement le nom du thème
        );
    }
}
