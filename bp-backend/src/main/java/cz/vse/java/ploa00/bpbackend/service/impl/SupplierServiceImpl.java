package cz.vse.java.ploa00.bpbackend.service.impl;

import cz.vse.java.ploa00.bpbackend.api.gen.model.SupplierDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.model.SupplierOrderDTO;
import cz.vse.java.ploa00.bpbackend.entity.ingredient.Ingredient;
import cz.vse.java.ploa00.bpbackend.entity.supplier.Supplier;
import cz.vse.java.ploa00.bpbackend.entity.supplier.order.SupplierOrder;
import cz.vse.java.ploa00.bpbackend.entity.supplier.order.SupplierOrderLine;
import cz.vse.java.ploa00.bpbackend.exception.ResourceNotFoundException;
import cz.vse.java.ploa00.bpbackend.repository.IngredientRepository;
import cz.vse.java.ploa00.bpbackend.repository.SupplierOrderRepository;
import cz.vse.java.ploa00.bpbackend.repository.SupplierRepository;
import cz.vse.java.ploa00.bpbackend.service.SupplierService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierServiceImpl  implements SupplierService {

    private SupplierRepository supplierRepository;

    private SupplierOrderRepository supplierOrderRepository;

    private IngredientRepository ingredientRepository;

    private ModelMapper modelMapper;

    @Override
    public SupplierDTO addSupplier(SupplierDTO supplierDTO) {

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        Supplier savedSupplier = supplierRepository.save(supplier);

        return modelMapper.map(savedSupplier, SupplierDTO.class);
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {

        List<Supplier> allSuppliers = supplierRepository.findAll();

        return allSuppliers.stream().map((supplier -> modelMapper.map(supplier, SupplierDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getSupplierById(Long supplierId) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with given id: " + supplierId));

        return modelMapper.map(supplier, SupplierDTO.class);
    }

    @Override
    public SupplierDTO updateSupplier(Long supplierId, SupplierDTO supplierDTO) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with given id: " + supplierId));

        supplier.setName(supplierDTO.getName());
        supplier.setDescription(supplierDTO.getDescription());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setPhone(supplierDTO.getPhone());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return modelMapper.map(updatedSupplier, SupplierDTO.class);
    }

    @Override
    public void deleteSupplier(Long supplierId) {

        if (supplierRepository.existsById(supplierId)) {
            supplierRepository.deleteById(supplierId);
        } else {
            throw new ResourceNotFoundException("Supplier not found with given id: " + supplierId);
        }

    }

    @Override
    public SupplierOrderDTO addSupplierOrder(SupplierOrderDTO supplierOrderDTO) {

        SupplierOrder supplierOrder = modelMapper.map(supplierOrderDTO, SupplierOrder.class);

        SupplierOrder savedSupplierOrder = supplierOrderRepository.save(supplierOrder);

        return modelMapper.map(savedSupplierOrder, SupplierOrderDTO.class);
    }

    @Override
    public SupplierOrderDTO updateSupplierOrder(Long supplierOrderId, SupplierOrderDTO supplierOrderDTO) {

        supplierOrderRepository.findById(supplierOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found with given id: " + supplierOrderId));

        SupplierOrder supplierOrder = modelMapper.map(supplierOrderDTO, SupplierOrder.class);

        supplierOrder.setId(supplierOrderId);

//        if (supplierOrder.getIsReceived()) {
//            throw new OrderStateException("Supplier Order with given id: " + supplierOrderId + " is already received.");
//        }

        SupplierOrder updatedSupplierOrder = supplierOrderRepository.save(supplierOrder);

        return modelMapper.map(updatedSupplierOrder, SupplierOrderDTO.class);
    }

    @Override
    public List<SupplierOrderDTO> getAllSupplierOrders() {

        List<SupplierOrder> allSuppliers = supplierOrderRepository.findAll();

        return allSuppliers.stream().map((supplierOrder -> modelMapper.map(supplierOrder, SupplierOrderDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierOrderDTO getSupplierOrderById(Long supplierOrderId) {

        SupplierOrder supplierOrder = supplierOrderRepository.findById(supplierOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found with given id: " + supplierOrderId));

        return modelMapper.map(supplierOrder, SupplierOrderDTO.class);
    }

    @Override
    public void deleteSupplierOrder(Long supplierOrderId) {

        SupplierOrder supplierOrder = supplierOrderRepository.findById(supplierOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found with given id: " + supplierOrderId));

        if (supplierOrderId.equals(supplierOrder.getId())) {
//            if (supplierOrder.getIsReceived()) {
//            throw new OrderStateException("Supplier Order with given id: " + supplierOrderId + " is already received.");
//        }
            supplierOrderRepository.deleteById(supplierOrderId);
        } else {
            throw new ResourceNotFoundException("Supplier Order not found with given id: " + supplierOrderId);
        }
    }

    @Override
    public void receiveOrder(Long supplierOrderId) {

        SupplierOrder supplierOrder = supplierOrderRepository.findById(supplierOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found with given id: " + supplierOrderId));

//        if (supplierOrder.getIsReceived()) {
//            throw new OrderStateException("Supplier Order with given id: " + supplierOrderId + " is already received.");
//        }

        supplierOrder.setIsReceived(true);

        List<SupplierOrderLine> orderLines = supplierOrder.getSupplierOrderLines();

//        if (orderLines.isEmpty()) {
//            throw new OrderStateException("Supplier Order with given id: " + supplierOrderId + " contains no lines");
//        }

        for (SupplierOrderLine line :orderLines) {
            Ingredient ingredient = line.getIngredient();
            BigDecimal stockIncrement = line.getQuantity();
            ingredient.setStock(stockIncrement.add(ingredient.getStock()));
            ingredientRepository.save(ingredient);
        }

        supplierOrderRepository.save(supplierOrder);
    }
}
