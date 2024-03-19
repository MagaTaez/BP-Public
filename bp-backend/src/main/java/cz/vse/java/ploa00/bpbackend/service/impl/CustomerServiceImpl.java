package cz.vse.java.ploa00.bpbackend.service.impl;

import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerDTO;
import cz.vse.java.ploa00.bpbackend.api.gen.model.CustomerOrderDTO;
import cz.vse.java.ploa00.bpbackend.entity.customer.Customer;
import cz.vse.java.ploa00.bpbackend.entity.customer.order.CustomerOrder;
import cz.vse.java.ploa00.bpbackend.entity.customer.order.CustomerOrderLine;
import cz.vse.java.ploa00.bpbackend.entity.ingredient.Ingredient;
import cz.vse.java.ploa00.bpbackend.entity.product.Product;
import cz.vse.java.ploa00.bpbackend.entity.recipe.ProductIngredient;
import cz.vse.java.ploa00.bpbackend.exception.OrderStateException;
import cz.vse.java.ploa00.bpbackend.exception.ResourceNotFoundException;
import cz.vse.java.ploa00.bpbackend.exception.StockException;
import cz.vse.java.ploa00.bpbackend.repository.CustomerOrderRepository;
import cz.vse.java.ploa00.bpbackend.repository.CustomerRepository;
import cz.vse.java.ploa00.bpbackend.repository.IngredientRepository;
import cz.vse.java.ploa00.bpbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustomerOrderRepository customerOrderRepository;

    private IngredientRepository ingredientRepository;

    private ModelMapper modelMapper;
    @Override
    public CustomerDTO addSCustomer(CustomerDTO customerDTO) {

        Customer customer = modelMapper.map(customerDTO, Customer.class);

        Customer savedCustomer = customerRepository.save(customer);

        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream().map((customer -> modelMapper.map(customer, CustomerDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with given id: " + customerId));

        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {

        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with given id: " + customerId));

        Customer customer = modelMapper.map(customerDTO, Customer.class);

        customer.setId(customerId);

        Customer updatedCustomer = customerRepository.save(customer);

        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(Long customerId) {

        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            throw new ResourceNotFoundException("Customer not found with given id: " + customerId);
        }

    }

    @Override
    public CustomerOrderDTO addCustomerOrder(CustomerOrderDTO customerOrderDTO) {

        CustomerOrder customerOrder = modelMapper.map(customerOrderDTO, CustomerOrder.class);

        CustomerOrder savedCustomerOrder = customerOrderRepository.save(customerOrder);

        return modelMapper.map(savedCustomerOrder, CustomerOrderDTO.class);
    }

    @Override
    public CustomerOrderDTO updateCustomerOrder(Long customerOrderId, CustomerOrderDTO customerOrderDTO) {

        customerOrderRepository.findById(customerOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Order not found with given id: " + customerOrderId));

        CustomerOrder customerOrder = modelMapper.map(customerOrderDTO, CustomerOrder.class);

        customerOrder.setId(customerOrderId);

        if (customerOrder.getIsDelivered()) {
            throw new OrderStateException("Customer Order with given id: " + customerOrderId + " is already delivered. It can be updated");
        }

        CustomerOrder updatedCustomerOrder = customerOrderRepository.save(customerOrder);

        return modelMapper.map(updatedCustomerOrder, CustomerOrderDTO.class);
    }

    @Override
    public List<CustomerOrderDTO> getAllCustomerOrders() {

        List<CustomerOrder> allCustomerOrders = customerOrderRepository.findAll();

        return allCustomerOrders.stream().map((customerOrder -> modelMapper.map(customerOrder, CustomerOrderDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerOrderDTO getCustomerOrderById(Long customerOrderId) {

        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Order not found with given id: " + customerOrderId));

        return modelMapper.map(customerOrder, CustomerOrderDTO.class);
    }

    @Override
    public void deleteCustomerOrder(Long customerOrderId) {

        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Order not found with given id: " + customerOrderId));

        if (customerOrderId.equals(customerOrder.getId())) {
            if (customerOrder.getIsDelivered()) {
                throw new OrderStateException("Customer Order with given id: " + customerOrderId + " is already delivered. It can be deleted.");
            }
            customerOrderRepository.deleteById(customerOrderId);
        } else {
            throw new ResourceNotFoundException("Customer Order not found with given id: " + customerOrderId);
        }

    }

    @Override
    public void deliverOrder(Long customerOrderId) {

        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Order not found with given id: " + customerOrderId));

        if (customerOrder.getIsDelivered()) {
            throw new OrderStateException("Customer Order with given id: " + customerOrderId + " is already delivered.");
        }

        customerOrder.setIsDelivered(true);

        List<CustomerOrderLine> orderLines = customerOrder.getCustomerOrderLines();

        for (CustomerOrderLine line : orderLines) {
            Product product = line.getProduct();
            BigDecimal productQuantity = line.getQuantity();
            List<ProductIngredient> productIngredients = product.getProductIngredients();

            for (ProductIngredient productIngredient : productIngredients) {
                Ingredient ingredient = productIngredient.getIngredient();
                BigDecimal requiredQuantity = productIngredient.getQuantity();
                BigDecimal totalRequiredQuantity = requiredQuantity.multiply(productQuantity);
                BigDecimal availableStock = ingredient.getStock();

                if (totalRequiredQuantity.compareTo(availableStock) > 0) {
                    throw new StockException("Insufficient stock for ingredient: " + ingredient.getName());
                }

                ingredient.setStock(availableStock.subtract(totalRequiredQuantity));
                ingredientRepository.save(ingredient);
            }
        }

        customerOrderRepository.save(customerOrder);
    }
}
