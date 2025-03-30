package com.network.models;

import java.time.LocalDateTime;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Users can comment articles even if it is not their own
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Lob
    private String content;

    @NonNull
    private LocalDateTime date;

    @NonNull
    private String author;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
