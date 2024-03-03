package cz.vse.java.ploa00.bpbackend.entity.customer.order;

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
@Embeddable
public class CustomerOrderLine {

    @Column
    private BigDecimal quantity;

    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    /* Relations */

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrderLine that = (CustomerOrderLine) o;
        return Objects.equals(quantity, that.quantity) && Objects.equals(sellPrice, that.sellPrice) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, sellPrice, product);
    }

    @Override
    public String toString() {
        return "CustomerOrderLine{" +
                "quantity=" + quantity +
                ", sellPrice=" + sellPrice +
                ", product=" + product +
                '}';
    }
}
