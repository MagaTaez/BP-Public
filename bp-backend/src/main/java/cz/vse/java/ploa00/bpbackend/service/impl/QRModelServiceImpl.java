package cz.vse.java.ploa00.bpbackend.service.impl;

import cz.vse.java.ploa00.bpbackend.api.gen.model.QRModelDTO;
import cz.vse.java.ploa00.bpbackend.entity.qrmodel.QRModel;
import cz.vse.java.ploa00.bpbackend.exception.ResourceNotFoundException;
import cz.vse.java.ploa00.bpbackend.repository.QRModelRepository;
import cz.vse.java.ploa00.bpbackend.service.QRModelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QRModelServiceImpl implements QRModelService {

    private QRModelCalcService qrModelCalc;

    private QRModelRepository qrModelRepository;

    private ModelMapper modelMapper;

    @Override
    public QRModelDTO addQRModel(QRModelDTO qrModelDTO) {

        QRModel qrModel = modelMapper.map(qrModelDTO, QRModel.class);

        calculateQRModel(qrModel);

        QRModel savedQRModel = qrModelRepository.save(qrModel);

        return modelMapper.map(savedQRModel, QRModelDTO.class);
    }

    @Override
    public List<QRModelDTO> getAllQRModels() {
        List<QRModel> allModels = qrModelRepository.findAll();

        return allModels.stream().map((qrModel -> modelMapper.map(qrModel, QRModelDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public QRModelDTO getQRModelById(Long qrModelId) {

        QRModel qrModel = qrModelRepository.findById(qrModelId)
                .orElseThrow(() -> new ResourceNotFoundException("QR model not found with given ID" + qrModelId));

        return modelMapper.map(qrModel, QRModelDTO.class);
    }

    @Override
    public QRModelDTO updateQRModelDTO(Long qrModelId, QRModelDTO updatedQRModel) {

        qrModelRepository.findById(qrModelId)
                .orElseThrow(() -> new ResourceNotFoundException("QR model not found with given ID" + qrModelId));

        QRModel qrModel = modelMapper.map(updatedQRModel, QRModel.class);

        qrModel.setId(qrModelId);

        calculateQRModel(qrModel);

        QRModel newQRModel = qrModelRepository.save(qrModel);

        return modelMapper.map(newQRModel, QRModelDTO.class);
    }

    @Override
    public void deleteQRModel(Long qrModelId) {

        if (qrModelRepository.existsById(qrModelId)) {
            qrModelRepository.deleteById(qrModelId);
        } else {
            throw new ResourceNotFoundException("QR model not found with given ID" + qrModelId);
        }
    }

    private void calculateQRModel(QRModel qrModel) {
        BigDecimal demandLeadTime = qrModelCalc.calculateMeanLeadTimeDemand(
                qrModel.getDemand(),
                qrModel.getLeadTime()
        );

        qrModel.setDemandLeadTime(demandLeadTime);

        BigDecimal standardDeviationLeadTime = qrModelCalc.calculateSdLeadTimeDemand(
                qrModel.getStandardDeviation(),
                qrModel.getLeadTime()
        );

        qrModel.setStandardDeviationLeadTime(standardDeviationLeadTime);


        double eoq = qrModelCalc.calculateEOQ(
                qrModel.getOrderingCost(),
                qrModel.getDemand(),
                qrModel.getHoldingCost()
        );

        double zScore = qrModelCalc.calculateZScore(
                eoq,
                qrModel.getHoldingCost(),
                qrModel.getStockoutCost(),
                qrModel.getDemand()
        );

        double zValue = qrModelCalc.calculateZValue(zScore);

        BigDecimal safetyStock = qrModelCalc.calculateSafetyStock(
                zValue,
                qrModel.getStandardDeviationLeadTime()
        );

        qrModel.setSafetyStock(safetyStock);


        if (zScore < 0.5) {
            qrModel.setOrderQuantity(BigDecimal.valueOf(eoq));
            BigDecimal reorderPoint = qrModelCalc.calculateReorderPoint(
                    qrModel.getDemandLeadTime(),
                    qrModel.getSafetyStock()
            );
            qrModel.setReorderPoint(reorderPoint);
            BigDecimal totalCost = qrModelCalc.calculateTotalCost(
                    qrModel.getOrderQuantity().doubleValue(),
                    qrModel.getReorderPoint(),
                    qrModel.getDemand(),
                    qrModel.getDemandLeadTime(),
                    qrModel.getHoldingCost(),
                    qrModel.getOrderingCost()
            );
            qrModel.setTotalCost(totalCost);
        } else {
            double lZ = qrModelCalc.calculateStandardizedLossFunction(zScore);

            double nR = qrModelCalc.calculateNR(
                    lZ,
                    qrModel.getStandardDeviationLeadTime()
            );

            BigDecimal optimalOrderQuantity = qrModelCalc.calculateOptimalOrderQuantity(
                    nR,
                    qrModel.getDemand(),
                    qrModel.getOrderingCost(),
                    qrModel.getStockoutCost(),
                    qrModel.getHoldingCost()
            );

            qrModel.setOrderQuantity(optimalOrderQuantity);

            double newZScore = qrModelCalc.calculateZScore(
                    qrModel.getOrderQuantity().doubleValue(),
                    qrModel.getHoldingCost(),
                    qrModel.getStockoutCost(),
                    qrModel.getDemand()
            );

            double newZValue = qrModelCalc.calculateZValue(newZScore);

            BigDecimal optimalSafetyStock = qrModelCalc.calculateSafetyStock(
                    newZValue,
                    qrModel.getStandardDeviationLeadTime()
            );

            qrModel.setSafetyStock(optimalSafetyStock);

            BigDecimal optimalReorderPoint =qrModelCalc.calculateReorderPoint(
                    qrModel.getDemandLeadTime(),
                    qrModel.getSafetyStock()
            );

            qrModel.setReorderPoint(optimalReorderPoint);

            BigDecimal optimalTotalCost = qrModelCalc.calculateOptimalTotalCost(
                    qrModel.getOrderQuantity(),
                    qrModel.getReorderPoint(),
                    nR,
                    qrModel.getDemand(),
                    qrModel.getHoldingCost(),
                    qrModel.getOrderingCost(),
                    qrModel.getStockoutCost(),
                    qrModel.getDemandLeadTime()
            );

            qrModel.setTotalCost(optimalTotalCost);
        }
    }
}
