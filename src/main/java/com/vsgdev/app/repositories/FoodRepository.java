package com.vsgdev.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vsgdev.models.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long>{


}
