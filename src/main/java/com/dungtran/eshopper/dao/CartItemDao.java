package com.dungtran.eshopper.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dungtran.eshopper.entities.CartItem;
import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.entities.User;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	
	public List<CartItem> findByUser(User user);
	
	public CartItem findByUserAndProduct(User user,Product product);
	
	public List<CartItem> findByOrderAndUser(Order order,User user);

	public void deleteByOrderAndProductAndUser(Order order,Product product,User user);
	
	public int countByOrderAndUser(Order order,User user);
}
