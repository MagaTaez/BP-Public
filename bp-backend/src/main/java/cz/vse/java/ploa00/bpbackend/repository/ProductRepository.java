package cz.vse.java.ploa00.bpbackend.repository;

import cz.vse.java.ploa00.bpbackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
