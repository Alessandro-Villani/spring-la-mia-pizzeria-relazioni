package org.java.pizzeria.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepo pizzaRepo;
	
	public List<Pizza> findAll(){
		
		return pizzaRepo.findAll();
		
	}
	
	public Pizza save(Pizza pizza) {
		
		return pizzaRepo.save(pizza);
		
	}
	
	public Optional<Pizza> findById(int id) {
		
		return pizzaRepo.findById(id);
		
	}
	
	public List<Pizza> findByNameContaining(String name){
		
		return pizzaRepo.findByNameContaining(name);
		
	}
	
	@Transactional
	public Optional<Pizza> findByIdWithSpecialOffer(int id){
		
		Optional<Pizza> optPizza = pizzaRepo.findById(id);
		Hibernate.initialize(optPizza.get().getSpecialOffers());
		return optPizza;
		
	}
	
	public void deleteById(int id) {
		
		pizzaRepo.deleteById(id);
		
	}

}
