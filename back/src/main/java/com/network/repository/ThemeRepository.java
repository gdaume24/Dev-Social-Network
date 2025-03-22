package com.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.network.models.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
        
        Theme findByName(String name);
}