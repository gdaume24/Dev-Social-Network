package com.network.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime date;
    private Long articleId;
}
