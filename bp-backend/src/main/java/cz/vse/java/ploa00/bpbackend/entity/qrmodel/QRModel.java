package cz.vse.java.ploa00.bpbackend.entity.qrmodel;

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
@Table(name = "QR_MODEL")
public class QRModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_model_id")
    private Long id;

    @Column(nullable = false)
    private BigDecimal demand;

    @Column(name = "st_dev", nullable = false)
    private BigDecimal standardDeviation;

    @Column(name = "lead_time", nullable = false)
    private BigDecimal leadTime;

    @Column(name = "demand_lt", nullable = false)
    private BigDecimal demandLeadTime;

    @Column(name = "stdev_lt", nullable = false)
    private BigDecimal standardDeviationLeadTime;

    @Column(name = "ordering_cost", nullable = false)
    private BigDecimal orderingCost;

    @Column(name = "holding_cost", nullable = false)
    private BigDecimal holdingCost;

    @Column(name = "stockout_cost", nullable = false)
    private BigDecimal stockoutCost;

    @Column(name = "reorder_point", nullable = false)
    private BigDecimal reorderPoint;

    @Column(name = "order_quantity", nullable = false)
    private BigDecimal orderQuantity;

    @Column(name = "safety_stock", nullable = false)
    private BigDecimal safetyStock;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    /* Relations */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QRModel qrModel = (QRModel) o;
        return Objects.equals(id, qrModel.id) && Objects.equals(demand, qrModel.demand) && Objects.equals(standardDeviation, qrModel.standardDeviation) && Objects.equals(leadTime, qrModel.leadTime) && Objects.equals(demandLeadTime, qrModel.demandLeadTime) && Objects.equals(standardDeviationLeadTime, qrModel.standardDeviationLeadTime) && Objects.equals(orderingCost, qrModel.orderingCost) && Objects.equals(holdingCost, qrModel.holdingCost) && Objects.equals(stockoutCost, qrModel.stockoutCost) && Objects.equals(reorderPoint, qrModel.reorderPoint) && Objects.equals(orderQuantity, qrModel.orderQuantity) && Objects.equals(safetyStock, qrModel.safetyStock) && Objects.equals(totalCost, qrModel.totalCost) && Objects.equals(ingredient, qrModel.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, demand, standardDeviation, leadTime, demandLeadTime, standardDeviationLeadTime, orderingCost, holdingCost, stockoutCost, reorderPoint, orderQuantity, safetyStock, totalCost, ingredient);
    }

    @Override
    public String toString() {
        return "QRModel{" +
                "id=" + id +
                ", demand=" + demand +
                ", standardDeviation=" + standardDeviation +
                ", leadTime=" + leadTime +
                ", demandLeadTime=" + demandLeadTime +
                ", standardDeviationLeadTime=" + standardDeviationLeadTime +
                ", orderingCost=" + orderingCost +
                ", holdingCost=" + holdingCost +
                ", stockoutCost=" + stockoutCost +
                ", reorderPoint=" + reorderPoint +
                ", orderQuantity=" + orderQuantity +
                ", safetyStock=" + safetyStock +
                ", totalCost=" + totalCost +
                ", ingredient=" + ingredient +
                '}';
    }
}
