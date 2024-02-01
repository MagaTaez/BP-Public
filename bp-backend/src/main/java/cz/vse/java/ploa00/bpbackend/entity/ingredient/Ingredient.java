package cz.vse.java.ploa00.bpbackend.entity.ingredient;

import cz.vse.java.ploa00.bpbackend.entity.operation.IngredientOperation;
import cz.vse.java.ploa00.bpbackend.entity.qrmodel.QrModel;
import cz.vse.java.ploa00.bpbackend.entity.recipe.ProductIngredient;
import cz.vse.java.ploa00.bpbackend.entity.supplier.order.SupplierOrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "units_of_measure", nullable = false)
    private String unitsOfMeasure;

    @Column(precision = 10, scale = 2)
    private BigDecimal stock;

    /* Relations */

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductIngredient> productIngredients;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierOrderLine> supplierOrderLines;

    @OneToOne(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private QrModel qrModel;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientOperation> operations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(unitsOfMeasure, that.unitsOfMeasure) && Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, unitsOfMeasure, stock);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitsOfMeasure='" + unitsOfMeasure + '\'' +
                ", stock=" + stock +
                '}';
    }
}
