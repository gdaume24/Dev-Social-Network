package com.network.mapper;

import org.springframework.stereotype.Component;

import com.network.dto.CommentDto;
import com.network.models.Comment;

@Component
public class CommentMapper {
    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setDate(comment.getDate());
        commentDto.setArticleId(comment.getArticle().getId());
        return commentDto;
}
}
