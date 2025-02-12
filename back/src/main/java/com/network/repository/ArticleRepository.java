package com.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.network.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    
}
