package com.arth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arth.dto.ExpenseDto;
import com.arth.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {

	@Query(value = " select * from expenses where month(date) = :month and user_id = :userId",nativeQuery = true)
	List<ExpenseEntity> getCurrentMonthExpByUserId(Integer month,Integer userId);

	
	@Query(value = " select sum(amount) from expenses where month(date) = :month and user_id = :userId",nativeQuery = true)
	Integer getCurrentMonthExpAmountByUserId(Integer month,Integer userId);

	@Query(value = " select sum(amount) from expenses where year(date) = :year and user_id = :userId",nativeQuery = true)
	Integer getCurrentYearExpAmountByUserId(Integer year,Integer userId);

	
	@Query(value = "select e.title as expenseTitle ,e.expense_id as expenseId, e.amount,e.description,e.date, u.first_name as firstName,s.status_name as statusName ,a.title , v.vendor_name as vendorName ,c.category_name as categoryName,sc.subcategory_name as subCategoryName"
			+ " from expenses e, users u , status s,accounts a,vendors v,categories c,subcategories sc "
			+ "where e.user_id = u.user_id and s.status_id = e.status_id and a.account_id = e.account_id and v.vendor_id = e.vendor_id "
			+ "	and c.category_id = e.category_id and sc.subcategory_id = e.subcategory_id",nativeQuery = true)
	List<ExpenseDto> getAllExpenses();
	

	@Query(value = " select sum(amount),month(date) from expenses group by month(date)",nativeQuery = true)
	List<Integer[]> getExpenseSumByMonth();

}
