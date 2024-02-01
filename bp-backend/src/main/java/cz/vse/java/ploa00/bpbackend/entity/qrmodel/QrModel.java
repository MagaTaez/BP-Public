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
public class QrModel {

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

    @Column(name = "purchase_cost", nullable = false)
    private BigDecimal purchaseCost;

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

    /* Relations */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QrModel qrModel = (QrModel) o;
        return Objects.equals(id, qrModel.id) && Objects.equals(demand, qrModel.demand) && Objects.equals(standardDeviation, qrModel.standardDeviation) && Objects.equals(leadTime, qrModel.leadTime) && Objects.equals(demandLeadTime, qrModel.demandLeadTime) && Objects.equals(standardDeviationLeadTime, qrModel.standardDeviationLeadTime) && Objects.equals(orderingCost, qrModel.orderingCost) && Objects.equals(purchaseCost, qrModel.purchaseCost) && Objects.equals(holdingCost, qrModel.holdingCost) && Objects.equals(stockoutCost, qrModel.stockoutCost) && Objects.equals(reorderPoint, qrModel.reorderPoint) && Objects.equals(orderQuantity, qrModel.orderQuantity) && Objects.equals(safetyStock, qrModel.safetyStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, demand, standardDeviation, leadTime, demandLeadTime, standardDeviationLeadTime, orderingCost, purchaseCost, holdingCost, stockoutCost, reorderPoint, orderQuantity, safetyStock);
    }

    @Override
    public String toString() {
        return "QrModel{" +
                "id=" + id +
                ", demand=" + demand +
                ", standardDeviation=" + standardDeviation +
                ", leadTime=" + leadTime +
                ", demandLeadTime=" + demandLeadTime +
                ", standardDeviationLeadTime=" + standardDeviationLeadTime +
                ", orderingCost=" + orderingCost +
                ", purchaseCost=" + purchaseCost +
                ", holdingCost=" + holdingCost +
                ", stockoutCost=" + stockoutCost +
                ", reorderPoint=" + reorderPoint +
                ", orderQuantity=" + orderQuantity +
                ", safetyStock=" + safetyStock +
                '}';
    }
}
