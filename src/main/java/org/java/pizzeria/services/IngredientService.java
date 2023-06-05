package org.java.pizzeria.services;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Ingredient;
import org.java.pizzeria.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ingredientRepo;
	
	public List<Ingredient> findAll(){
		
		return ingredientRepo.findAll();
		
	}
	
	public Ingredient save(Ingredient ingredient) {
		
		return ingredientRepo.save(ingredient);
		
	}
	
	public Optional<Ingredient> findById(int id) {
		
		return ingredientRepo.findById(id);
		
	}
	
	public void deleteById(int id) {
		
		ingredientRepo.deleteById(id);
		
	}
	
}
