package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerOrderDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.rest.CustomersApiDelegate;
import cz.vse.java.ploa00.bpbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomersApiDelegateImpl extends AbstractDelegate implements CustomersApiDelegate {

    private CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDTO> addCustomer(CustomerDTO customerDTO) {
        checkManagerRole();

        CustomerDTO savedCustomer = customerService.addSCustomer(customerDTO);

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerOrderDTO> addCustomerOrder(CustomerOrderDTO customerOrderDTO) {
        checkEmployeeRole();

        CustomerOrderDTO savedCustomerOrder = customerService.addCustomerOrder(customerOrderDTO);

        return new ResponseEntity<>(savedCustomerOrder, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        checkManagerRole();

        customerService.deleteCustomer(customerId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteCustomerOrder(Long orderId) {
        checkManagerRole();

        customerService.deleteCustomerOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deliverOrder(Long orderId) {
        checkEmployeeRole();

        customerService.deliverOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CustomerOrderDTO>> getAllCustomerOrders() {
        checkEmployeeRole();

        List<CustomerOrderDTO> allCustomerOrders = customerService.getAllCustomerOrders();

        return ResponseEntity.ok(allCustomerOrders);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        checkEmployeeRole();

        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        return ResponseEntity.ok(allCustomers);

    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(Long customerId) {
        checkEmployeeRole();

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerOrderDTO> getCustomerOrderById(Long orderId) {
        checkEmployeeRole();

        CustomerOrderDTO customerOrderDTO = customerService.getCustomerOrderById(orderId);

        return new ResponseEntity<>(customerOrderDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDTO> updateCustomer(Long customerId, CustomerDTO customerDTO) {
        checkManagerRole();

        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerDTO);

        return ResponseEntity.ok(updatedCustomer);
    }

    @Override
    public ResponseEntity<CustomerOrderDTO> updateCustomerOrder(Long orderId, CustomerOrderDTO customerOrderDTO) {
        checkManagerRole();

        CustomerOrderDTO updatedCustomerOrder = customerService.updateCustomerOrder(orderId, customerOrderDTO);

        return new ResponseEntity<>(updatedCustomerOrder, HttpStatus.CREATED);
    }
}
