package cz.vse.java.ploa00.bpbackend.entity.customer.order;

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
public class CustomerOrderLineCompositeKey implements Serializable {

    @Column(name = "customer_order_id")
    private Long customerOrderId;

    @Column(name = "product_id")
    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrderLineCompositeKey that = (CustomerOrderLineCompositeKey) o;
        return Objects.equals(customerOrderId, that.customerOrderId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerOrderId, productId);
    }

    @Override
    public String toString() {
        return "CustomerOrderLineCompositeKey{" +
                "customerOrderId=" + customerOrderId +
                ", productId=" + productId +
                '}';
    }
}
