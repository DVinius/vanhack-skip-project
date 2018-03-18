package com.vsgdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsgdev.app.repositories.FoodRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food/")
public class FoodController {
	@Autowired 
	private FoodRepository foodRepository;

	@GetMapping(value = "/query")
	public ResponseEntity getFoodList() {
		return new ResponseEntity(foodRepository.findAll(), HttpStatus.OK);
	}
	
	/*
	
	@GetMapping(value = "/news/highestid")
	public ResponseEntity getHighestId() {
		return new ResponseEntity(this.newsDAO.getHighestNewsId(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/registerVote")
	public ResponseEntity registerVote(@RequestBody News news) {
		System.out.println("nid: "+news.getNid()+" votesUp: "+news.getVotesUp()+" votesDown: "+news.getVotesDown());
		news = this.newsDAO.registerVote(news);
		return new ResponseEntity(news, HttpStatus.OK);
		
	}
	*/
	
}
