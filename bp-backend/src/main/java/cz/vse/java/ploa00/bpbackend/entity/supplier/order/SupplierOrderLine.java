package cz.vse.java.ploa00.bpbackend.entity.supplier.order;

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
@Embeddable
public class SupplierOrderLine {

    @Column
    private BigDecimal quantity;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    /* Relations */

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierOrderLine that = (SupplierOrderLine) o;
        return Objects.equals(quantity, that.quantity) && Objects.equals(purchasePrice, that.purchasePrice) && Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, purchasePrice, ingredient);
    }

    @Override
    public String toString() {
        return "SupplierOrderLine{" +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", ingredient=" + ingredient +
                '}';
    }
}
