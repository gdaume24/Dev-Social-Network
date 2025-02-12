package com.network.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private Date date;
    private List<String> themes;
}
