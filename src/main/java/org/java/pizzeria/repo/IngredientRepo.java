package org.java.pizzeria.repo;

import org.java.pizzeria.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer>{

}
