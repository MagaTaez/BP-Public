package cz.vse.java.ploa00.bpbackend.repository;

import cz.vse.java.ploa00.bpbackend.entity.supplier.order.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {
}
