package cz.vse.java.ploa00.bpbackend.service;

import cz.vse.java.ploa00.bpbackend.api.gen.model.SupplierDTO;

import java.util.List;

public interface SupplierService {

    SupplierDTO addSupplier(SupplierDTO supplierDTO);

    List<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierById(Long supplierId);

    SupplierDTO updateSupplier(Long supplierId, SupplierDTO supplierDTO);

    void deleteSupplier(Long supplierId);
}
