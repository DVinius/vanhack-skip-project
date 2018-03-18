package com.vsgdev.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsgdev.app.repositories.FoodRepository;
import com.vsgdev.app.repositories.OrderItemRepository;
import com.vsgdev.app.repositories.OrderRepository;
import com.vsgdev.enums.OrderStatus;
import com.vsgdev.models.Food;
import com.vsgdev.models.FoodOrder;
import com.vsgdev.models.OrderAmount;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order/")
public class OrderController {
	@Autowired 
	private OrderRepository orderRepository;
	@Autowired 
	private FoodRepository foodRepository;
	@Autowired 
	private OrderItemRepository orderItemRepository;
	
	@GetMapping(value = "/cancel")
	public ResponseEntity cancelOrder(@RequestParam long id) {
		final Optional<FoodOrder> foodOrder = this.orderRepository.findById(id);
		if (!foodOrder.isPresent()) {
			return new ResponseEntity<String>("This order id does not exists!", HttpStatus.OK);
		} else {
			foodOrder.get().setStatus(OrderStatus.CANCELED);
			this.orderRepository.save(foodOrder.get());
			return new ResponseEntity<String>("Order canceled", HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/status")
	public ResponseEntity getOrderStatus(@RequestParam long id) {
		final Optional<FoodOrder> foodOrder = this.orderRepository.findById(id);
		if (foodOrder.isPresent()) {
			return new ResponseEntity<String>("This order id does not exists!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(foodOrder.get().getStatus().toString(), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/register")
	public ResponseEntity registerOrder(@RequestBody FoodOrder order) {
		order.setDate(Calendar.getInstance());
		
		if (order.getOrderAmount().isEmpty()) {
			return new ResponseEntity<String>("Empty order items and quantities", HttpStatus.OK);
		} else {
			final FoodOrder registeredOrder = new FoodOrder();
			registeredOrder.setDate(Calendar.getInstance());
			registeredOrder.setStatus(OrderStatus.PENDING);
			this.orderRepository.save(registeredOrder);
			
			registeredOrder.setFoodList(new ArrayList<Food>());
			
			final List<OrderAmount> itemsOrder = new ArrayList<OrderAmount>();
			
			for (OrderAmount foodAndQty: order.getOrderAmount()) {
				final Food food = foodAndQty.getFood();
				if (food != null) {
					final Optional<Food> validFood = foodRepository.findById(foodAndQty.getFood().getId());
					if (validFood.get() == null) {
						return new ResponseEntity<String>("Food id not valid!", HttpStatus.OK);
					} else {
						final OrderAmount itemsAndQty = new OrderAmount();
						itemsAndQty.setFood(validFood.get());
						itemsAndQty.setFoodOrder(registeredOrder);
						itemsAndQty.setAmount(foodAndQty.getAmount());
						this.orderItemRepository.save(itemsAndQty);
						itemsOrder.add(itemsAndQty);
						//food.setAmount(null);
						registeredOrder.getFoodList().add(validFood.get());
						registeredOrder.setTotalValue((float) (registeredOrder.getTotalValue( ) + (validFood.get().getPrice() * foodAndQty.getAmount())));
					}
				} else {
					return new ResponseEntity<String>("Food is empty!", HttpStatus.OK);
				}
				
			}
			
			this.orderRepository.save(registeredOrder);
			
			return new ResponseEntity<FoodOrder>(registeredOrder, HttpStatus.OK);
		}
		
	}
}
