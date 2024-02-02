package cz.vse.java.ploa00.bpbackend.dto.qrmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QrModelDto {

    private Long id;
    private BigDecimal demand;
    private BigDecimal standardDeviation;
    private BigDecimal leadTime;
    private BigDecimal demandLeadTime;
    private BigDecimal standardDeviationLeadTime;
    private BigDecimal orderingCost;
    private BigDecimal purchaseCost;
    private BigDecimal holdingCost;
    private BigDecimal stockoutCost;
    private BigDecimal reorderPoint;
    private BigDecimal orderQuantity;
    private BigDecimal safetyStock;
}
