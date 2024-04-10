package cz.vse.java.ploa00.bpbackend.service;

import cz.vse.java.ploa00.bpbackend.api.gen.model.QRModelDTO;

import java.util.List;

public interface QRModelService {
    QRModelDTO addQRModel(QRModelDTO qrModelDTO);

    List<QRModelDTO> getAllQRModels();

    QRModelDTO getQRModelById(Long qrModelId);

    QRModelDTO updateQRModelDTO(Long qrModelId, QRModelDTO updatedQRModel);

    void deleteQRModel(Long qrModelId);
}
