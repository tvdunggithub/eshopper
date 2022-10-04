package com.dungtran.eshopper.services.Impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.dao.ProductDao;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;

	@Override
	public Page<Product> findByGenderCategory(String genderCategory,Pageable pageable) {
		return productDao.findByGenderCategory(genderCategory,pageable);
	}

	@Override
	public Page<Product> findByNameAndGender(String gc,String name,Pageable pageable) {
		return productDao.findByNameAndGender(gc,name,pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productDao.findAll(pageable);
	}

	@Override
	public int countByGenderCategory(String gc) {
		return productDao.countByGenderCategory(gc);
	}

	@Override
	public Page<Product> findByCategory(String gc, String c,Pageable pageable) {
		return productDao.findByCategory(gc, c,pageable);
	}

	@Override
	public Page<Product> findByName(String name, Pageable pageable) {
		return productDao.findByName(name, pageable);
	}


	@Override
	public Page<Product> findByNameAndPrice(String name, Long startPrice, Long endPrice,Pageable pageable) {
		return productDao.findByNameAndPrice(name, startPrice, endPrice, pageable);
	}

	@Override
	public Page<Product> findByGenderCategoryAndPrice(String gc, Long startPrice, Long endPrice,Pageable pageable) {
		return productDao.findByGenderCategoryAndPrice(gc, startPrice, endPrice, pageable);
	}

	@Override
	public Page<Product> findByGenderCategoryPriceCategory(String gc, String c, Long startPrice, Long endPrice,Pageable pageable) {
		return productDao.findByGenderCategoryPriceCategory(gc, c, startPrice, endPrice, pageable);
	}

	@Override
	public Page<Product> findByGenderCategoryPriceKeyword(String gc, String keyword, Long startPrice, Long endPrice,Pageable pageable) {
		return productDao.findByGenderCategoryPriceKeyword(gc, keyword, startPrice, endPrice, pageable);
	}

	@Override
	public Optional<Product> findById(int id) {
		return productDao.findById(id);
	}


}
