package org.java.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Ingredient;
import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.services.IngredientService;
import org.java.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/ingredients")
	public String goToIngredientIndex(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return "ingredients-index";
		
	}
	
	@GetMapping("/ingredients/create")
	public String createIngredient(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "ingredient-create";
		
	}
	
	@PostMapping("ingredients/create")
	public String storeIngredient(Model model, @Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("ingredient", ingredient);
			model.addAttribute("errors", bindingResult);
			
			return "ingredient-create";
		}
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredients";
	}
	
	@GetMapping("/ingredients/edit/{id}")
	public String editIngredient(Model model, @PathVariable int id) {
		
		Optional<Ingredient> optIngredient = ingredientService.findById(id);
		Ingredient ingredient = optIngredient.get();
		
		model.addAttribute("ingredient", ingredient);
		
		return "ingredient-edit";
		
	}
	
	@PostMapping("ingredients/edit/{id}")
	public String updateIngredient(Model model, @PathVariable int id, @ModelAttribute @Valid Ingredient ingredient, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("ingredient", ingredient);
			model.addAttribute("errors", bindingResult);
			
			return "ingredient-edit";
			
		}
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredients";
	}
	
	@PostMapping("ingredients/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id) {
		
		for (Pizza pizza : pizzaService.findAll()) {
			
			pizza.removeIngredient(ingredientService.findById(id).get());
			pizzaService.save(pizza);
			
		}
		
		ingredientService.deleteById(id);
		
		return "redirect:/ingredients";
	}

}
