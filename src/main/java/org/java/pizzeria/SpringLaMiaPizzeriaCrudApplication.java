package org.java.pizzeria;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.Ingredient;
import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.pojo.SpecialOffer;
import org.java.pizzeria.services.IngredientService;
import org.java.pizzeria.services.PizzaService;
import org.java.pizzeria.services.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private IngredientService ingredientService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Ingredient i1 = new Ingredient("pomodoro");
		Ingredient i2 = new Ingredient("mozzarella");
		Ingredient i3 = new Ingredient("prosciutto crudo");
		Ingredient i4 = new Ingredient("grana padano DOP");
		Ingredient i5 = new Ingredient("rucola");
		Ingredient i6 = new Ingredient("prosciutto cotto");
		Ingredient i7 = new Ingredient("funghi champignon");
		Ingredient i8 = new Ingredient("salame piccante");
		Ingredient i9 = new Ingredient("salsiccia");
		Ingredient i10 = new Ingredient("friarielli");
		
		ingredientService.save(i1);
		ingredientService.save(i2);
		ingredientService.save(i3);
		ingredientService.save(i4);
		ingredientService.save(i5);
		ingredientService.save(i6);
		ingredientService.save(i7);
		ingredientService.save(i8);
		ingredientService.save(i9);
		ingredientService.save(i10);
		
		Pizza p1 = new Pizza("Margherita", "Pomodoro e mozzarella", "https://static.cookist.it/wp-content/uploads/sites/21/2018/04/pizza-margherita-fatta-in-casa.jpg", 5.50, i1, i2);
		Pizza p2 = new Pizza("Crudo, grana e rucola", "Pomodoro, Mozzarella, Prosciutto crudo, Grana padano DOP, Rucola", "https://blog.giallozafferano.it/primipiattiricette/wp-content/uploads/2015/10/gluten-free-pizza.jpg", 7.00, i1, i2, i3, i4, i5);
		Pizza p3 = new Pizza("Prosciutto e funghi", "Pomodoro, mozzarella, prosciutto cotto, funghi champignon", "https://www.petitchef.it/imgupl/recipe/pizza-al-prosciutto-e-funghi-la-ricetta-spiegata-passo-a-passo--455633p707852.jpg", 6.00, i1, i2, i6, i7);
		Pizza p4 = new Pizza("Diavola", "Pomodoro, mozzarella, salame piccante", "https://www.iffco.it/sites/default/files/styles/free_crop/public/img/recipe/gran-cucina-pizza-diavola.jpg?h=de92a0b7&itok=eC0EvTVI", 6.00, i1, i2, i8);
		Pizza p5 = new Pizza("Salsiccia e friarielli", "Mozzarella, salsiccia, friarielli", "https://staticcookist.akamaized.net/wp-content/uploads/sites/21/2022/06/pizza-salsiccia-friarielli-storia.jpg", 7.50, i2, i9, i10);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		
		List<Pizza> pizze = pizzaService.findAll();
		
		System.out.println(pizze);
		
		SpecialOffer s1 = new SpecialOffer("under 18", LocalDate.now(), LocalDate.of(2023, 06, 07), 20, p1);
		SpecialOffer s2 = new SpecialOffer("over 60", LocalDate.now(), LocalDate.of(2023, 06, 15), 30, p1);
		SpecialOffer s3 = new SpecialOffer("2x1", LocalDate.now(), LocalDate.of(2023, 06, 12), 50, p2);
		SpecialOffer s4 = new SpecialOffer("special discount", LocalDate.now(), LocalDate.of(2023, 06, 25), 15, p4);
		
		specialOfferService.save(s1);
		specialOfferService.save(s2);
		specialOfferService.save(s3);
		specialOfferService.save(s4);
		
		for(Pizza pizza : pizze) {
			
			Optional<Pizza> optPizzaOffer = pizzaService.findByIdWithSpecialOffer(pizza.getId());
			Pizza pizzaOffer = optPizzaOffer.get();
			System.out.println(pizzaOffer.getSpecialOffers());
			
		}
		
		
	}

}
