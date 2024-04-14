package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.IngredientDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.IngredientsApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientsApiDelegateImpl extends AbstractDelegate implements IngredientsApiDelegate {

    private IngredientService ingredientService;
    @Override
    public ResponseEntity<IngredientDTO> addIngredient(IngredientDTO ingredientDTO) {
        checkManagerRole();

        IngredientDTO savedIngredient = ingredientService.addIngredient(ingredientDTO);

        return new ResponseEntity<>(savedIngredient, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        checkEmployeeRole();

        List<IngredientDTO> allIngredients = ingredientService.getAllIngredients();

        return ResponseEntity.ok(allIngredients);
    }

    @Override
    public ResponseEntity<IngredientDTO> getIngredientById(Long ingredientId) {
        checkEmployeeRole();

        IngredientDTO ingredientDTO = ingredientService.getIngredientById(ingredientId);

        return new ResponseEntity<>(ingredientDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IngredientDTO> updateIngredient(Long ingredientId, IngredientDTO ingredientDTO) {
        checkManagerRole();

        IngredientDTO updatedIngredient = ingredientService.updateIngredient(ingredientId, ingredientDTO);

        return ResponseEntity.ok(updatedIngredient);
    }

    @Override
    public ResponseEntity<Void> deleteIngredient(Long ingredientId) {
        checkManagerRole();

        ingredientService.deleteIngredient(ingredientId);

        return ResponseEntity.ok().build();
    }
}
