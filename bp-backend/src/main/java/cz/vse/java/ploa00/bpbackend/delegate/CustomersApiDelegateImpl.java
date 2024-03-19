package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerOrderDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.CustomersApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomersApiDelegateImpl implements CustomersApiDelegate {

    private CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDTO> addCustomer(CustomerDTO customerDTO) {

        CustomerDTO savedCustomer = customerService.addSCustomer(customerDTO);

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerOrderDTO> addCustomerOrder(CustomerOrderDTO customerOrderDTO) {

        CustomerOrderDTO savedCustomerOrder = customerService.addCustomerOrder(customerOrderDTO);

        return new ResponseEntity<>(savedCustomerOrder, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteCustomerOrder(Long orderId) {

        customerService.deleteCustomerOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deliverOrder(Long orderId) {

        customerService.deliverOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CustomerOrderDTO>> getAllCustomerOrders() {

        List<CustomerOrderDTO> allCustomerOrders = customerService.getAllCustomerOrders();

        return ResponseEntity.ok(allCustomerOrders);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        return ResponseEntity.ok(allCustomers);
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(Long customerId) {

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerOrderDTO> getCustomerOrderById(Long orderId) {

        CustomerOrderDTO customerOrderDTO = customerService.getCustomerOrderById(orderId);

        return new ResponseEntity<>(customerOrderDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDTO> updateCustomer(Long customerId, CustomerDTO customerDTO) {

        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerDTO);

        return ResponseEntity.ok(updatedCustomer);
    }

    @Override
    public ResponseEntity<CustomerOrderDTO> updateCustomerOrder(Long orderId, CustomerOrderDTO customerOrderDTO) {

        CustomerOrderDTO updatedCustomerOrder = customerService.updateCustomerOrder(orderId, customerOrderDTO);

        return new ResponseEntity<>(updatedCustomerOrder, HttpStatus.CREATED);
    }
}
