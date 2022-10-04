package com.dungtran.eshopper.services.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.dao.CartItemDao;
import com.dungtran.eshopper.dao.ProductDao;
import com.dungtran.eshopper.entities.CartItem;
import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CartItemDao cartItemDao;

	@Override
	public int addToCart(int id, int quantity, User user) {
		
		Product product = productDao.findById(id).get();
		CartItem cartItem = cartItemDao.findByUserAndProduct(user, product);
		
		if(cartItem != null) {
			int addedQuantity = cartItem.getQuantity() + quantity;
			cartItem.setQuantity(addedQuantity);
		}else {
			cartItem = new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setUser(user);
			cartItem.setProduct(product);
		}
		cartItemDao.save(cartItem);
		
		return 0;
	}

	@Override
	public List<CartItem> findByUser(User user) {
		return cartItemDao.findByUser(user);
	}

	@Override
	public List<CartItem> findByOrderAndUser(Order order, User user) {
		return cartItemDao.findByOrderAndUser(order, user);
	}

	@Override
	public int countSubtotal(Order order,User user) {
		List<CartItem> cart = cartItemDao.findByOrderAndUser(order,user);
		int total = 0;
		for(CartItem c:cart) {
			total += c.getQuantity()*c.getProduct().getPrice();
		}		
		return total;
	}
	
	@Override
	public int countTotal(Order order,User user) {
		List<CartItem> cart = cartItemDao.findByOrderAndUser(order,user);
		int subTotal = countSubtotal(order,user);
		int total = subTotal + cart.size()*2;
		return total;
	}
	
	@Override
	public void deleteByOrderAndProduct(Order order,Product product,User user) {
		cartItemDao.deleteByOrderAndProductAndUser(order,product,user);
	}

	@Override
	public int countByOrderAndUser(Order order, User user) {
		return cartItemDao.countByOrderAndUser(order, user);
	}

	@Override
	public void save(CartItem cartItem) {
		cartItemDao.save(cartItem);		
	}

}
