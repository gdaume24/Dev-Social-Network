package com.network.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Article model, representing an article writted by a user
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String title;

  @NonNull
  private String author;

  @NonNull
  @Column( length = 100000 )
  private String content;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp date;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  /**
   * The article belongs to one or many themes
  */
  @ManyToMany
  @JoinTable(
          name = "article_themes", // Nom de la table intermédiaire
          joinColumns = @JoinColumn(name = "article_id"), // Clé étrangère vers Articles
          inverseJoinColumns = @JoinColumn(name = "theme_id") // Clé étrangère vers Theme
  )
  @Builder.Default
  private List<Theme> themes = new ArrayList<>();
}
