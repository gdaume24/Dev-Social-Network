package com.network.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.network.dto.ArticleDto;
import com.network.models.Article;

@Component
@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article> {
    
}
