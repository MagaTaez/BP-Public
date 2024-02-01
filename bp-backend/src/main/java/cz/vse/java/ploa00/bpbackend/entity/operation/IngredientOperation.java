package cz.vse.java.ploa00.bpbackend.entity.operation;

import cz.vse.java.ploa00.bpbackend.entity.ingredient.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INGREDIENT_OPERATION")
public class IngredientOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Long id;

    @Column(name = "operation_date", nullable = false)
    private LocalDateTime operationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private OperationType operationType;

    @Column(nullable = false)
    private BigDecimal quantity;

    public enum OperationType {
        PURCHASE,
        SALE
    }

    /* Relations */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientOperation that = (IngredientOperation) o;
        return Objects.equals(id, that.id) && Objects.equals(operationDate, that.operationDate) && operationType == that.operationType && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operationDate, operationType, quantity);
    }

    @Override
    public String toString() {
        return "IngredientOperation{" +
                "id=" + id +
                ", operationDate=" + operationDate +
                ", operationType=" + operationType +
                ", quantity=" + quantity +
                '}';
    }
}
