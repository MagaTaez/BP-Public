package cz.vse.java.ploa00.bpbackend.service;

import cz.vse.java.ploa00.bpbackend.api.gen.model.IngredientDTO;

import java.util.List;

public interface IngredientService {

    IngredientDTO  addIngredient(IngredientDTO ingredientDTO);

    List<IngredientDTO> getAllIngredients();

    IngredientDTO getIngredientById(Long ingredientId);

    IngredientDTO updateIngredient(Long ingredientId, IngredientDTO updatedIngredient);

    void deleteIngredient(Long ingredientId);
}
