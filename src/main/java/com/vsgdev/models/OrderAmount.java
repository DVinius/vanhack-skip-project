package com.vsgdev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderAmount implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long lid;
	@ManyToOne
	private FoodOrder foodOrder;
	@ManyToOne
	private Food food;
	private int amount;
	
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public FoodOrder getFoodOrder() {
		return foodOrder;
	}
	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
