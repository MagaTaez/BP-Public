package cz.vse.java.ploa00.bpbackend.repository;

import cz.vse.java.ploa00.bpbackend.entity.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
