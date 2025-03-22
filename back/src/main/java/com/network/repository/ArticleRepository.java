package com.network.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.network.models.Article;
import com.network.models.Theme;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByThemeIn(List<Theme> themes);
}
