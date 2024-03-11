package com.arth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arth.entity.SubcategoryEntity;

public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Integer> {
	
}
