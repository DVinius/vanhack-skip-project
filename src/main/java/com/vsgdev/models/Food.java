package com.vsgdev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Food {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long fid;
	private String description;
	private double price;
	@OneToMany
	@Transient
	private List<OrderAmount> amount;
	
	public List<OrderAmount> getAmount() {
		return amount;
	}
	public void setAmount(List<OrderAmount> amount) {
		this.amount = amount;
	}
	
	public Long getId() {
		return fid;
	}
	public void setId(Long id) {
		this.fid = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
