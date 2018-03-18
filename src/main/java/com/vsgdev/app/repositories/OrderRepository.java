package com.vsgdev.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vsgdev.models.FoodOrder;

@Repository
public interface OrderRepository extends CrudRepository<FoodOrder, Long>{
	/*
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean registerOrder(final FoodOrder order) {
		this.jdbcTemplate.update("insert into FOOD_ORDER (orderdatetime ) values(?)", new Object[] {order.getTotalValue()}, new Object[] {Types.DATE});
		return true;
	}
	*/

}
