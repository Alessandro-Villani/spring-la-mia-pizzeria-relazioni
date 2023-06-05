package org.java.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Ingredient;
import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.pojo.SpecialOffer;
import org.java.pizzeria.services.IngredientService;
import org.java.pizzeria.services.PizzaService;
import org.java.pizzeria.services.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/pizzas")
	public String goToPizzaIndex(Model model) {
		
		List<Pizza> pizzaList = pizzaService.findAll();
		
		model.addAttribute("pizzaList", pizzaList);
		
		return "index";
	}
	
	@PostMapping("/pizzas")
	public String goToPizzaIndexResearch(Model model, @RequestParam(required = false) String name) {
		
		List<Pizza> pizzaList = pizzaService.findByNameContaining(name);
		
		model.addAttribute("pizzaList", pizzaList);
		model.addAttribute("searchTerm", name);
		
		return "index";
	}
	
	@GetMapping("/pizzas/{id}")
	public String goToPizzaDetails(Model model, @PathVariable("id") int id) {
		
		Optional<Pizza> optPizza = pizzaService.findByIdWithSpecialOffer(id);
		
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "details";
	}
	
	
	@GetMapping("pizzas/create")
	public String getCreate(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizza", new Pizza());
		
		return "create";
	}
	
	@PostMapping("pizzas/create")
	public String storePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "create";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/pizzas";
	}
	
	@GetMapping("pizzas/edit/{id}")
	public String editPizza(Model model, @PathVariable int id) {
		
		
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "edit";
		
	}
	
	@PostMapping("pizzas/update/{id}")
	public String updatePizza(Model model, @PathVariable int id, @ModelAttribute @Valid Pizza pizza, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "edit";
			
		}

		pizzaService.save(pizza);
		
		return "redirect:/pizzas/" + id;

	}
	
	@PostMapping("pizzas/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		Pizza pizza = pizzaService.findById(id).get();
		
		for(SpecialOffer so : pizza.getSpecialOffers()) {
			
			specialOfferService.deleteById(so.getId());
			
		}
		
		pizzaService.deleteById(id);
		
		return "redirect:/pizzas";
	}
}
