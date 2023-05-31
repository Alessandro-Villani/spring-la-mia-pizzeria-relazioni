package org.java.pizzeria.controller;

import java.util.Optional;

import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.pojo.SpecialOffer;
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

import jakarta.validation.Valid;

@Controller
public class SpecialOfferController {

	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("pizzas/{pizzaId}/special-offer/create")
	public String getSpecialOfferCreate(@PathVariable int pizzaId, Model model){
		
		model.addAttribute("specialOffer", new SpecialOffer());
		model.addAttribute("pizzaId", pizzaId);
		
		return "special-offer-create";
		
	}
	
	@PostMapping("pizzas/{pizzaId}/special-offer/create")
	public String storeSpecialOffer(@PathVariable int pizzaId, Model model, @Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("specialOffer", specialOffer);
			model.addAttribute("errors", bindingResult);
			
			return "special-offer-create";
		}
		
		specialOfferService.save(specialOffer);
		
		return "redirect:/pizzas/" + specialOffer.getPizza().getId();
	}
	
	@GetMapping("pizzas/{pizzaId}/special-offer/edit/{id}")
	public String editSpecialOffer(Model model, @PathVariable int pizzaId, @PathVariable int id) {
		
		Optional<SpecialOffer> optSpecialOffer = specialOfferService.findById(id);
		SpecialOffer specialOffer = optSpecialOffer.get();
		
		model.addAttribute("pizzaId", pizzaId);
		model.addAttribute("specialOffer", specialOffer);
		
		return "special-offer-edit";
		
	}
	
	@PostMapping("pizzas/{pizzaId}/special-offer/update/{id}")
	public String updatePizza(Model model, @PathVariable int pizzaId, @PathVariable int id, @ModelAttribute @Valid SpecialOffer specialOffer, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("specialOffer", specialOffer);
			model.addAttribute("errors", bindingResult);
			
			return "special-offer-edit";
			
		}
		
		specialOfferService.save(specialOffer);
		
		return "redirect:/pizzas/" + pizzaId;

	}
	
}
