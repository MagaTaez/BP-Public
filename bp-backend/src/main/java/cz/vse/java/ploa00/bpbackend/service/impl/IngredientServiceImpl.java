package cz.vse.java.ploa00.bpbackend.service.impl;

import cz.vse.java.ploa00.bpbackend.api.gen.model.IngredientDTO;
import cz.vse.java.ploa00.bpbackend.entity.ingredient.Ingredient;
import cz.vse.java.ploa00.bpbackend.exception.ResourceNotFoundException;
import cz.vse.java.ploa00.bpbackend.repository.IngredientRepository;
import cz.vse.java.ploa00.bpbackend.service.IngredientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    private ModelMapper modelMapper;

    @Override
    public IngredientDTO addIngredient(IngredientDTO ingredientDTO) {

        Ingredient ingredient = modelMapper.map(ingredientDTO, Ingredient.class);

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return modelMapper.map(savedIngredient, IngredientDTO.class);
    }

    @Override
    public List<IngredientDTO> getAllIngredients() {

        List<Ingredient> allIngredients = ingredientRepository.findAll();

        return allIngredients.stream().map((ingredient -> modelMapper.map(ingredient, IngredientDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public IngredientDTO getIngredientById(Long ingredientId) {

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with given ID" + ingredientId));

        return modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public IngredientDTO updateIngredient(Long ingredientId, IngredientDTO updatedIngredient) {

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with given ID" + ingredientId));

        ingredient.setName(updatedIngredient.getName());
        ingredient.setDescription(updatedIngredient.getDescription());
        ingredient.setStock(updatedIngredient.getStock());
        ingredient.setUnitsOfMeasure(updatedIngredient.getUnitsOfMeasure().getValue());

        Ingredient editedIngredient = ingredientRepository.save(ingredient);

        return modelMapper.map(editedIngredient, IngredientDTO.class);
    }

    @Override
    public void deleteIngredient(Long ingredientId) {

        if (ingredientRepository.existsById(ingredientId)) {
            ingredientRepository.deleteById(ingredientId);
        } else {
            throw new ResourceNotFoundException("Ingredient not found with given ID" + ingredientId);
        }
    }
}
