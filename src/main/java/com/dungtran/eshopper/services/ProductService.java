package com.dungtran.eshopper.services;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.entities.Product;

@Service
@Transactional
public interface ProductService {
	
	public Optional<Product> findById(int id);
	
	public Page<Product> findAll(Pageable pageable);
	
	public Page<Product> findByGenderCategory(String genderCategory,Pageable pageable);
	
	public Page<Product> findByCategory(String gc,String c,Pageable pageable);
	
	public Page<Product> findByNameAndGender(String gc,String name,Pageable pageable);
	
	public Page<Product> findByName(String name,Pageable pageable);
	
	public Page<Product> findByNameAndPrice(String name, Long startPrice, Long endPrice,Pageable pageable);
	
	public Page<Product> findByGenderCategoryAndPrice(String gc, Long startPrice, Long endPrice,Pageable pageable);
	
	public Page<Product> findByGenderCategoryPriceCategory(String gc,String c, Long startPrice, Long endPrice,Pageable pageable);
	
	public Page<Product> findByGenderCategoryPriceKeyword(String gc,String keyword, Long startPrice, Long endPrice,Pageable pageable);
	
	public int countByGenderCategory(String gc);
}
