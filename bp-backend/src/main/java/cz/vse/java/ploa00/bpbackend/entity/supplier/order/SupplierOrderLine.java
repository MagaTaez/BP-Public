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
@Entity
@Table(name = "SUPPLIER_ORDER_LINE")
public class SupplierOrderLine {

    @EmbeddedId
    private SupplierOrderLineCompositeKey supplierOrderLineCompositeKey;

    @Column
    private BigDecimal quantity;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    /* Relations */

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("supplierOrderId")
    @JoinColumn(name = "supplier_order_id")
    private SupplierOrder supplierOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierOrderLine that = (SupplierOrderLine) o;
        return Objects.equals(supplierOrderLineCompositeKey, that.supplierOrderLineCompositeKey) && Objects.equals(quantity, that.quantity) && Objects.equals(purchasePrice, that.purchasePrice) && Objects.equals(supplierOrder, that.supplierOrder) && Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierOrderLineCompositeKey, quantity, purchasePrice, supplierOrder, ingredient);
    }

    @Override
    public String toString() {
        return "SupplierOrderLine{" +
                "supplierOrderLineCompositeKey=" + supplierOrderLineCompositeKey +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", supplierOrder=" + supplierOrder +
                ", ingredient=" + ingredient +
                '}';
    }
}
