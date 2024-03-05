package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.SupplierDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.model.SupplierOrderDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.SuppliersApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SuppliersApiDelegateImpl implements SuppliersApiDelegate {

    private SupplierService supplierService;

    @Override
    public ResponseEntity<SupplierDTO> addSupplier(SupplierDTO supplierDTO) {

        SupplierDTO savedSupplier = supplierService.addSupplier(supplierDTO);

        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSupplier(Long supplierId) {

        supplierService.deleteSupplier(supplierId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {

        List<SupplierDTO> allSuppliers = supplierService.getAllSuppliers();

        return ResponseEntity.ok(allSuppliers);
    }

    @Override
    public ResponseEntity<SupplierDTO> getSupplierById(Long supplierId) {

        SupplierDTO supplierDTO = supplierService.getSupplierById(supplierId);

        return new ResponseEntity<>(supplierDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SupplierDTO> updateSupplier(Long supplierId, SupplierDTO supplierDTO) {

        SupplierDTO updatedSupplier = supplierService.updateSupplier(supplierId, supplierDTO);

        return ResponseEntity.ok(updatedSupplier);
    }

    @Override
    public ResponseEntity<SupplierOrderDTO> addSupplierOrder(SupplierOrderDTO supplierOrderDTO) {
        SupplierOrderDTO savedSupplierOrderDTO = supplierService.addSupplierOrder(supplierOrderDTO);

        return new ResponseEntity<>(savedSupplierOrderDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSupplierOrder(Long orderId) {
        supplierService.deleteSupplierOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SupplierOrderDTO>> getAllSupplierOrders() {
        List<SupplierOrderDTO> allSupplierOrders = supplierService.getAllSupplierOrders();

        return ResponseEntity.ok(allSupplierOrders);
    }

    @Override
    public ResponseEntity<SupplierOrderDTO> getSupplierOrderById(Long orderId) {
        SupplierOrderDTO supplierOrderDTO = supplierService.getSupplierOrderById(orderId);

        return new ResponseEntity<>(supplierOrderDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> receiveOrder(Long orderId) {

        supplierService.receiveOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SupplierOrderDTO> updateSupplierOrder(Long orderId, SupplierOrderDTO supplierOrderDTO) {

        SupplierOrderDTO updatedSupplierOrderDTO = supplierService.updateSupplierOrder(orderId, supplierOrderDTO);

        return new ResponseEntity<>(updatedSupplierOrderDTO, HttpStatus.CREATED);
    }
}
