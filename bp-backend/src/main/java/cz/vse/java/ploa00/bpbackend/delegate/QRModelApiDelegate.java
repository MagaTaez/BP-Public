package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.QRModelDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.QrmodelsApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.QRModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QRModelApiDelegate extends AbstractDelegate implements QrmodelsApiDelegate {

    private QRModelService qrModelService;

    @Override
    public ResponseEntity<QRModelDTO> addQRModel(QRModelDTO qrModelDTO) {
        checkManagerRole();

        QRModelDTO savedQRModel = qrModelService.addQRModel(qrModelDTO);

        return new ResponseEntity<>(savedQRModel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteQRModel(Long qrmodelId) {
        checkManagerRole();

        qrModelService.deleteQRModel(qrmodelId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<QRModelDTO>> getAllQRModels() {
        checkEmployeeRole();

        List<QRModelDTO> allModels = qrModelService.getAllQRModels();

        return ResponseEntity.ok(allModels);
    }

    @Override
    public ResponseEntity<QRModelDTO> getQRModelById(Long qrmodelId) {
        checkEmployeeRole();

        QRModelDTO qrModelDTO = qrModelService.getQRModelById(qrmodelId);

        return new ResponseEntity<>(qrModelDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<QRModelDTO> updateQRModel(Long qrmodelId, QRModelDTO qrModelDTO) {
        checkManagerRole();

        QRModelDTO updatedQRModel = qrModelService.updateQRModelDTO(qrmodelId, qrModelDTO);

        return ResponseEntity.ok(updatedQRModel);
    }
}
