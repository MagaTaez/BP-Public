package cz.vse.java.ploa00.bpbackend.service.impl;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class QRModelCalcService {

    public BigDecimal calculateMeanLeadTimeDemand(BigDecimal meanDemand, BigDecimal leadTime) {
        return meanDemand.multiply(leadTime);
    }

    public BigDecimal calculateSdLeadTimeDemand(BigDecimal standardDeviation, BigDecimal leadTime) {
        double sqrtLeadTime = Math.sqrt(leadTime.doubleValue());
        return standardDeviation.multiply(BigDecimal.valueOf(sqrtLeadTime));
    }

    public double calculateEOQ(BigDecimal orderingCost, BigDecimal meanDemand, BigDecimal holdingCost) {
        return Math.sqrt((2 * orderingCost.doubleValue() * meanDemand.doubleValue()) / holdingCost.doubleValue());
    }

    public double calculateZScore(double eoq, BigDecimal holdingCost, BigDecimal shortageCost, BigDecimal meanDemand) {
        return 1 - ((eoq * holdingCost.doubleValue()) / (eoq * holdingCost.doubleValue() + shortageCost.doubleValue() * meanDemand.doubleValue()));
    }

    public double calculateZValue(double zScore) {
        NormalDistribution distribution = new NormalDistribution();
        return distribution.inverseCumulativeProbability(zScore);
    }

    public BigDecimal calculateSafetyStock(double zValue, BigDecimal sdDemandLt) {
        double safetyStock = zValue * sdDemandLt.doubleValue();
        return BigDecimal.valueOf(safetyStock);
    }

    public BigDecimal calculateReorderPoint(BigDecimal meanDemandLt, BigDecimal safetyStock) {
        double reorderPoint = meanDemandLt.doubleValue() + safetyStock.doubleValue();
        return BigDecimal.valueOf(reorderPoint);
    }

    public BigDecimal calculateTotalCost(double eoq,
                                         BigDecimal reorderPoint,
                                         BigDecimal meanDemand,
                                         BigDecimal meanDemandLt,
                                         BigDecimal holdingCost,
                                         BigDecimal orderingCost) {
        double totalCost = holdingCost.doubleValue() * (eoq / 2 + reorderPoint.doubleValue() - meanDemandLt.doubleValue()) + (orderingCost.doubleValue() * meanDemand.doubleValue()) / eoq;
        return BigDecimal.valueOf(totalCost);
    }

    public double calculateStandardizedLossFunction(double zScore) {
        NormalDistribution distribution = new NormalDistribution();
        double pdf = distribution.density(zScore);
        double cdf = distribution.cumulativeProbability(zScore);
        return pdf - zScore * (1 - cdf);
    }

    public double calculateNR(double lZ, BigDecimal sdDemandLt) {
        return sdDemandLt.doubleValue() * lZ;
    }

    public BigDecimal calculateOptimalOrderQuantity(double nR,
                                                    BigDecimal meanDemand,
                                                    BigDecimal orderingCost,
                                                    BigDecimal shortageCost,
                                                    BigDecimal holdingCost) {
        double optimalOrderQuantity = Math.sqrt((2 * meanDemand.doubleValue() * (orderingCost.doubleValue() + shortageCost.doubleValue() * nR)) / holdingCost.doubleValue());
        return BigDecimal.valueOf(optimalOrderQuantity);
    }

    public BigDecimal calculateOptimalTotalCost(BigDecimal orderQuantity,
                                         BigDecimal reorderPoint,
                                         double nR,
                                         BigDecimal meanDemand,
                                         BigDecimal holdingCost,
                                         BigDecimal orderingCost,
                                         BigDecimal shortageCost,
                                         BigDecimal meanDemandLt) {
        double optimalTotalCost = holdingCost.doubleValue() * (orderQuantity.doubleValue() / 2 + reorderPoint.doubleValue() - meanDemandLt.doubleValue()) + (orderingCost.doubleValue() * meanDemand.doubleValue()) / orderQuantity.doubleValue() + (shortageCost.doubleValue() * meanDemand.doubleValue() * nR) / orderQuantity.doubleValue();
        return BigDecimal.valueOf(optimalTotalCost);
    }
}
