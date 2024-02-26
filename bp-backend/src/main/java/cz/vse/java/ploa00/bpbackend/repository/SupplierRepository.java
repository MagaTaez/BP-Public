package cz.vse.java.ploa00.bpbackend.repository;

import cz.vse.java.ploa00.bpbackend.entity.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
