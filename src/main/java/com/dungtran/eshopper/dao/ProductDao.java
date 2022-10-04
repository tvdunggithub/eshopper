package com.dungtran.eshopper.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dungtran.eshopper.entities.Product;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {
	
	
	@Query("SELECT p FROM Product p WHERE p.genderCategory = :gc")
	public Page<Product> findByGenderCategory(@Param("gc")String gc,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.genderCategory = :gc and p.name LIKE CONCAT('%',:name,'%') ")
	public Page<Product> findByNameAndGender(@Param("gc")String gc,@Param("name")String name,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.genderCategory = :gc and p.category = :c")
	public Page<Product> findByCategory(@Param("gc")String gc,@Param("c")String c,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%') ")
	public Page<Product> findByName(@Param("name")String name,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%') AND p.price >= :startPrice AND p.price <=:endPrice")
	public Page<Product> findByNameAndPrice(@Param("name")String name,@Param("startPrice")Long startPrice,@Param("endPrice")Long endPrice,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.genderCategory = :gc and p.price >= :startPrice and p.price <=:endPrice")
	public Page<Product> findByGenderCategoryAndPrice(@Param("gc")String gc,@Param("startPrice")Long startPrice,@Param("endPrice")Long endPrice,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.genderCategory = :gc and p.category = :c and p.price >= :startPrice and p.price <=:endPrice")
	public Page<Product> findByGenderCategoryPriceCategory(@Param("gc")String gc,@Param("c")String c,@Param("startPrice")Long startPrice,@Param("endPrice")Long endPrice,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.genderCategory = :gc and p.name LIKE CONCAT('%',:name,'%') and p.price >= :startPrice and p.price <=:endPrice")
	public Page<Product> findByGenderCategoryPriceKeyword(@Param("gc")String gc,@Param("name")String name,@Param("startPrice")Long startPrice,@Param("endPrice")Long endPrice,Pageable pageable);
	
	
	public int countByGenderCategory(String gc);
	
	
	

	
	
	

}
