package net.springBootAuthentication.springBootAuthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Name")
	private String Name;
	
	@Column(name = "Stack")
	private int Stack;
	
	@Column(name = "Price")
	private float Price;
	
	
	public Product() {
		super();
	}
	
	public Product(long id, String name, int stack, float price) {
		super();
		this.id = id;
		this.Name = name;
		this.Stack = stack;
		this.Price = price;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getStack() {
		return Stack;
	}
	public void setStack(int stack) {
		Stack = stack;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		this.Price = price;
	}
}
