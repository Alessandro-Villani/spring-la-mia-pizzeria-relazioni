package org.java.pizzeria;

import java.util.List;

import org.java.pizzeria.pojo.Pizza;
import org.java.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pizza p1 = new Pizza("Margherita", "Pomodoro e mozzarella", "https://static.cookist.it/wp-content/uploads/sites/21/2018/04/pizza-margherita-fatta-in-casa.jpg", 5.50);
		Pizza p2 = new Pizza("Crudo, grana e rucola", "Pomodoro, Mozzarella, Prosciutto crudo, Grana padano DOP, Rucola", "https://blog.giallozafferano.it/primipiattiricette/wp-content/uploads/2015/10/gluten-free-pizza.jpg", 7.00);
		Pizza p3 = new Pizza("Prosciutto e funghi", "Pomodoro, mozzarella, prosciutto cotto, funghi champignon", "https://www.petitchef.it/imgupl/recipe/pizza-al-prosciutto-e-funghi-la-ricetta-spiegata-passo-a-passo--455633p707852.jpg", 6.00);
		Pizza p4 = new Pizza("Diavola", "Pomodoro, mozzarella, salame piccante", "https://www.iffco.it/sites/default/files/styles/free_crop/public/img/recipe/gran-cucina-pizza-diavola.jpg?h=de92a0b7&itok=eC0EvTVI", 6.00);
		Pizza p5 = new Pizza("Salsiccia e friarielli", "Mozzarella, salsiccia, friarielli", "https://staticcookist.akamaized.net/wp-content/uploads/sites/21/2022/06/pizza-salsiccia-friarielli-storia.jpg", 7.50);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		
		List<Pizza> pizze = pizzaService.findAll();
		
		System.out.println(pizze);
		
		
	}

}
