package cz.vse.java.ploa00.bpbackend.entity.supplier.order;

import cz.vse.java.ploa00.bpbackend.entity.supplier.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUPPLIER_ORDER")
public class SupplierOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_order_id")
    private Long id;

    @Column(name = "order_date", nullable = false)
    private OffsetDateTime orderDate;

    @Column(name = "order_delivery_date", nullable = false)
    private OffsetDateTime orderDeliveryDate;

    @Column(name = "is_received", nullable = false)
    private Boolean isReceived;

    /* Relations */

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SUPPLIER_ORDER_LINE", joinColumns = @JoinColumn(name = "supplier_order_id"))
    @OrderColumn(name = "line_order")
    private List<SupplierOrderLine> supplierOrderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierOrder that = (SupplierOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(orderDate, that.orderDate) && Objects.equals(orderDeliveryDate, that.orderDeliveryDate) && Objects.equals(isReceived, that.isReceived) && Objects.equals(supplier, that.supplier) && Objects.equals(supplierOrderLines, that.supplierOrderLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, orderDeliveryDate, isReceived, supplier, supplierOrderLines);
    }

    @Override
    public String toString() {
        return "SupplierOrder{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", orderDeliveryDate=" + orderDeliveryDate +
                ", isReceived=" + isReceived +
                ", supplier=" + supplier +
                ", supplierOrderLines=" + supplierOrderLines +
                '}';
    }
}
