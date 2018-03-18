package com.vsgdev.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.vsgdev.enums.OrderStatus;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long oid;
	private Calendar date;
	@OneToMany
	@Transient
	private List<OrderAmount> orderAmount;
	private float totalValue;
	private OrderStatus status;
	@Transient
	private List<Food> foodList;
	
	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

	public List<OrderAmount> getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(List<OrderAmount> orderAmount) {
		this.orderAmount = orderAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Long getId() {
		return oid;
	}
	public void setId(Long id) {
		this.oid = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	public float getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}
	
}
