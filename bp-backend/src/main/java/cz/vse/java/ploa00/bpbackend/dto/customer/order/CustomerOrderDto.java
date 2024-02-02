package cz.vse.java.ploa00.bpbackend.dto.customer.order;

import cz.vse.java.ploa00.bpbackend.entity.customer.Customer;
import cz.vse.java.ploa00.bpbackend.entity.customer.order.CustomerOrderLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private Boolean isDelivered;
    private Customer customer;
    private List<CustomerOrderLine> customerOrderLines;
}
