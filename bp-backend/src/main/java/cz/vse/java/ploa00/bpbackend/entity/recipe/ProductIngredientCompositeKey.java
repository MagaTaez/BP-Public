package cz.vse.java.ploa00.bpbackend.entity.recipe;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductIngredientCompositeKey implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductIngredientCompositeKey that = (ProductIngredientCompositeKey) o;
        return Objects.equals(productId, that.productId) && Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, ingredientId);
    }

    @Override
    public String toString() {
        return "ProductIngredientCompositeKey{" +
                "productId=" + productId +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
