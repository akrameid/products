package com.akram.product.service;

import com.akram.product.dto.customer.AddCustomerRequestDto;
import com.akram.product.dto.customer.CustomerDto;
import com.akram.product.exception.CustomerIdNotFoundException;
import com.akram.product.exception.CustomerNameExistedException;
import com.akram.product.exception.CustomerNameNotFoundException;
import com.akram.product.model.Customer;
import com.akram.product.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public List<CustomerDto> getAll() {
        List<CustomerDto> customers = new ArrayList<>();
        for (Customer c : customerRepo.findAll()) {
            customers.add(CustomerDto.builder()
                    .name(c.getName())
                    .creditLimit(c.getCreditLimit())
                    .currentCredit(c.getCurrentCredit())
                    .id(c.getId())
                    .build());
        }
        return customers;
    }

    public String addCustomer(AddCustomerRequestDto addCustomerRequestDto) {
        String customerName = addCustomerRequestDto.getCustomerName();
        try {
            findByName(customerName);
            throw new CustomerNameExistedException(customerName);
        } catch (CustomerNameNotFoundException e) {
            Customer customer = Customer.builder()
                    .name(customerName)
                    .creditLimit(addCustomerRequestDto.getCreditLimit())
                    .currentCredit(addCustomerRequestDto.getCurrentCredit())
                    .build();
            customer = customerRepo.save(customer);
            return "Customer added successfully with id " + customer.getId();
        }
    }

    public String delete(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (!customer.isPresent())
            throw new CustomerIdNotFoundException(id);
        customerRepo.deleteById(id);
        return "Customer deleted successfully";
    }

    public Customer findByName(String customerName) {
        Optional<Customer> customer = customerRepo.findByName(customerName);
        if (!customer.isPresent())
            throw new CustomerNameNotFoundException(customerName);
        return customer.get();
    }

    void addCustomerCredit(Customer customer, long totalPrice) {
        customer.setCurrentCredit(customer.getCurrentCredit()+totalPrice);
        customerRepo.save(customer);
    }

    void adjustCredit(Customer customer, Long totalPrice) {
        customer.setCurrentCredit(customer.getCurrentCredit()-totalPrice);
        customerRepo.save(customer);
    }
}
