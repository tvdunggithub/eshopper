package com.dungtran.eshopper.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "order")
	private List<CartItem> cartItems;
	

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	private Date date;
	
	@Pattern(regexp="(^$|[0-9]{10,11})", message="Invalid phone number")
	@NotBlank(message="Adress is required")
	private String phone;
	
	@NotBlank(message="Adress is required")
	private String adress;
	
	@NotBlank(message="City is required")
	private String city;
	
	@NotBlank(message="First name is required")
	private String firstName;
	
	@NotBlank(message="Last name is required")
	private String lastName;
	
	
}
