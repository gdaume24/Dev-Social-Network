package com.network.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A theme of programmation (Java, Python, etc.), 
 * that user can subscribe to to get new articles published on their thread
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Theme {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The theme can be followed by one or many users
     */
    @ManyToMany(mappedBy = "themes") // Relation inverse
    @Builder.Default
    private Set<User> users = new HashSet<>();

    /**
     * The theme can be associated to one or many articles
     */
    @ManyToMany(mappedBy = "themes") // Relation inverse
    @Builder.Default
    private Set<Article> articles = new HashSet<>();

}
