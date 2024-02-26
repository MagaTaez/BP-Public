package cz.vse.java.ploa00.bpbackend.entity.customer.order;

import cz.vse.java.ploa00.bpbackend.entity.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_order_id")
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "is_delivered", nullable = false)
    private Boolean isDelivered;

    /* Relations */

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_order_id")
    @OrderColumn(name = "line_order")
    private List<CustomerOrderLine> customerOrderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(orderDate, that.orderDate) && Objects.equals(isDelivered, that.isDelivered) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, isDelivered, customer);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", isDelivered=" + isDelivered +
                ", customer=" + customer +
                '}';
    }
}
