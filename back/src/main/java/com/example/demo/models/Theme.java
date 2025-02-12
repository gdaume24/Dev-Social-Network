package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import com.network.models.User;

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

    @ManyToMany(mappedBy = "themes") // Relation inverse
    @Builder.Default
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "themes") // Relation inverse
    @Builder.Default
    private Set<Article> articles = new HashSet<>();

}
