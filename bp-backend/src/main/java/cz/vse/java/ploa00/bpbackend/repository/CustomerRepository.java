package cz.vse.java.ploa00.bpbackend.repository;

import cz.vse.java.ploa00.bpbackend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
