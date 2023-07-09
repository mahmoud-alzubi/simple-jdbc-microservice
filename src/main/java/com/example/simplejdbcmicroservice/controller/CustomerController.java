package com.example.simplejdbcmicroservice.controller;

import com.example.simplejdbcmicroservice.model.Customer;
import com.example.simplejdbcmicroservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable("id") long id) {
        Customer customer = customerRepository.findById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Customer> customersList = customerRepository.findAll();
        return ResponseEntity.ok(customersList);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Customer customer) {
        customerRepository.update(id, customer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        customerRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
