package com.network.dto;

import java.util.List;
import java.util.Set;

import com.network.models.Theme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private String title;
    private String content;
    private Set<Theme> theme;
}
