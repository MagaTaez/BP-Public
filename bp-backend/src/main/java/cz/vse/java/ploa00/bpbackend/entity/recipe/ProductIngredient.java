package cz.vse.java.ploa00.bpbackend.entity.recipe;

import cz.vse.java.ploa00.bpbackend.entity.ingredient.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_INGREDIENT")
public class ProductIngredient {

    @EmbeddedId
    private ProductIngredientCompositeKey productIngredientCompositeKey;

    @Column
    private BigDecimal quantity;

    /* Relations */

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductIngredient that = (ProductIngredient) o;
        return Objects.equals(productIngredientCompositeKey, that.productIngredientCompositeKey) && Objects.equals(quantity, that.quantity) && Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productIngredientCompositeKey, quantity, ingredient);
    }

    @Override
    public String toString() {
        return "ProductIngredient{" +
                "productIngredientCompositeKey=" + productIngredientCompositeKey +
                ", quantity=" + quantity +
                ", ingredient=" + ingredient +
                '}';
    }
}
