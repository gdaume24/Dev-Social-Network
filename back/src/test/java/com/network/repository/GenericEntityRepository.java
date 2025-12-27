package com.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.network.models.GenericEntity;

public interface GenericEntityRepository
  extends JpaRepository<GenericEntity, Long> { }