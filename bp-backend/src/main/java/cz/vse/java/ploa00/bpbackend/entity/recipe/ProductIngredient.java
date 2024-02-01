package cz.vse.java.ploa00.bpbackend.entity.recipe;

import cz.vse.java.ploa00.bpbackend.entity.ingredient.Ingredient;
import cz.vse.java.ploa00.bpbackend.entity.product.Product;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductIngredient that = (ProductIngredient) o;
        return Objects.equals(productIngredientCompositeKey, that.productIngredientCompositeKey) && Objects.equals(quantity, that.quantity) && Objects.equals(product, that.product) && Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productIngredientCompositeKey, quantity, product, ingredient);
    }

    @Override
    public String toString() {
        return "ProductIngredient{" +
                "productIngredientCompositeKey=" + productIngredientCompositeKey +
                ", quantity=" + quantity +
                ", product=" + product +
                ", ingredient=" + ingredient +
                '}';
    }
}
