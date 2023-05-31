package org.java.pizzeria.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Il titolo deve essere inserito")
	private String title;
	@NotNull(message = "La data iniziale deve essere specificato")
	private LocalDate startDate;
	@NotNull(message = "La data finale deve essere specificato")
	@Future(message = "La data finale deve essere nel futuro")
	private LocalDate endDate;
	@NotNull(message = "Lo sconto deve essere specificato")
	@Positive(message = "Lo sconto deve essere positivo")
	@Min(value = 1, message = "Lo sconto deve essere compreso tra 1 e 100")
	@Max(value = 100, message = "Lo sconto deve essere compreso tra 1 e 100")
	private Integer discountPercentage;
	
	@ManyToOne
	private Pizza pizza;
	
	public SpecialOffer() {}
	
	public SpecialOffer(String title, LocalDate startDate, LocalDate endDate, Integer discountPercentage, Pizza pizza) {
		
		setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setDiscountPercentage(discountPercentage);
		setPizza(pizza);
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public double getDiscountedPrice() {
		
		double pizzaPrice = getPizza().getPrice();
		double discountValue = pizzaPrice/100 * getDiscountPercentage();
		
		return  pizzaPrice - discountValue;
		
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "]" + getTitle() + " , Inizio: " + getStartDate() + " , fine: " + getEndDate() + " , sconto: " + getDiscountPercentage() + "%";
	}
	

}
