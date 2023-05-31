package org.java.pizzeria.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "La pizza deve avere un nome")
	private String name;
	@Column(columnDefinition = "text")
	@NotBlank(message = "La pizza deve avere una descrizione")
	private String description;
	@NotBlank(message = "La pizza deve avere un url dell'immagine")
	private String imageUrl;
	@Min(value = 0, message = "Il prezzo deve essere un valore positivo")
	private Double price;
	
	public Pizza() {}
	
	public Pizza(String name, String description, String imageUrl, Double price) {
		
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "] " + getName() + " " + getPrice() + "€." ;
	}
	
	

}