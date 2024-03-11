package com.arth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arth.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {

	@Query(value = " select * from expenses where month(date) = :month and user_id = :userId",nativeQuery = true)
	List<ExpenseEntity> getCurrentMonthExpByUserId(Integer month,Integer userId);

	
	@Query(value = " select sum(amount) from expenses where month(date) = :month and user_id = :userId",nativeQuery = true)
	Integer getCurrentMonthExpAmountByUserId(Integer month,Integer userId);

	@Query(value = " select sum(amount) from expenses where year(date) = :year and user_id = :userId",nativeQuery = true)
	Integer getCurrentYearExpAmountByUserId(Integer year,Integer userId);

	
}
