package cz.vse.java.ploa00.bpbackend.entity.supplier.order;

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
public class SupplierOrderLineCompositeKey implements Serializable {

    @Column(name = "supplier_order_id")
    private Long supplierOrderId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierOrderLineCompositeKey that = (SupplierOrderLineCompositeKey) o;
        return Objects.equals(supplierOrderId, that.supplierOrderId) && Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierOrderId, ingredientId);
    }

    @Override
    public String toString() {
        return "SupplierOrderLineCompositeKey{" +
                "customerOrderId=" + supplierOrderId +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
