package cz.vse.java.ploa00.bpbackend.entity.product;

import cz.vse.java.ploa00.bpbackend.entity.customer.order.CustomerOrderLine;
import cz.vse.java.ploa00.bpbackend.entity.recipe.ProductIngredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "sale_price", nullable = false)
    private Integer salePrice;

    /* Relations */

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductIngredient> productIngredients;

    @OneToMany(mappedBy = "product")
    private List<CustomerOrderLine> customerOrderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(salePrice, product.salePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, salePrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salePrice=" + salePrice +
                '}';
    }
}
