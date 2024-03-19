package cz.vse.java.ploa00.bpbackend.service;

import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerOrderDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO addSCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long customerId);

    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    CustomerOrderDTO addCustomerOrder(CustomerOrderDTO customerOrderDTO);

    CustomerOrderDTO updateCustomerOrder(Long customerOrderId, CustomerOrderDTO customerOrderDTO);

    List<CustomerOrderDTO> getAllCustomerOrders();

    CustomerOrderDTO getCustomerOrderById(Long customerOrderId);

    void deleteCustomerOrder(Long customerOrderId);

    void deliverOrder(Long customerOrderId);
}
