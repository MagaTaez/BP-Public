package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.SupplierDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.model.SupplierOrderDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.SuppliersApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SuppliersApiDelegateImpl extends AbstractDelegate implements SuppliersApiDelegate {

    private SupplierService supplierService;

    @Override
    public ResponseEntity<SupplierDTO> addSupplier(SupplierDTO supplierDTO) {
        checkManagerRole();

        SupplierDTO savedSupplier = supplierService.addSupplier(supplierDTO);

        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSupplier(Long supplierId) {
        checkManagerRole();

        supplierService.deleteSupplier(supplierId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        checkEmployeeRole();

        List<SupplierDTO> allSuppliers = supplierService.getAllSuppliers();

        return ResponseEntity.ok(allSuppliers);
    }

    @Override
    public ResponseEntity<SupplierDTO> getSupplierById(Long supplierId) {
        checkEmployeeRole();

        SupplierDTO supplierDTO = supplierService.getSupplierById(supplierId);

        return new ResponseEntity<>(supplierDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SupplierDTO> updateSupplier(Long supplierId, SupplierDTO supplierDTO) {
        checkManagerRole();

        SupplierDTO updatedSupplier = supplierService.updateSupplier(supplierId, supplierDTO);

        return ResponseEntity.ok(updatedSupplier);
    }

    @Override
    public ResponseEntity<SupplierOrderDTO> addSupplierOrder(SupplierOrderDTO supplierOrderDTO) {
        checkManagerRole();

        SupplierOrderDTO savedSupplierOrderDTO = supplierService.addSupplierOrder(supplierOrderDTO);

        return new ResponseEntity<>(savedSupplierOrderDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSupplierOrder(Long orderId) {
        checkManagerRole();

        supplierService.deleteSupplierOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SupplierOrderDTO>> getAllSupplierOrders() {
        checkEmployeeRole();

        List<SupplierOrderDTO> allSupplierOrders = supplierService.getAllSupplierOrders();

        return ResponseEntity.ok(allSupplierOrders);
    }

    @Override
    public ResponseEntity<SupplierOrderDTO> getSupplierOrderById(Long orderId) {
        checkEmployeeRole();

        SupplierOrderDTO supplierOrderDTO = supplierService.getSupplierOrderById(orderId);

        return new ResponseEntity<>(supplierOrderDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> receiveOrder(Long orderId) {
        checkEmployeeRole();

        supplierService.receiveOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SupplierOrderDTO> updateSupplierOrder(Long orderId, SupplierOrderDTO supplierOrderDTO) {
        checkManagerRole();

        SupplierOrderDTO updatedSupplierOrderDTO = supplierService.updateSupplierOrder(orderId, supplierOrderDTO);

        return new ResponseEntity<>(updatedSupplierOrderDTO, HttpStatus.CREATED);
    }
}
