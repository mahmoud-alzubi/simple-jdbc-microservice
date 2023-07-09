package com.example.simplejdbcmicroservice;

import com.example.simplejdbcmicroservice.model.Customer;
import com.example.simplejdbcmicroservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JdbcMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CustomerRepository customerRepository) {
        return runner -> {
            //Create Customer Table
            customerRepository.dropCustomerTableIfExists();
            customerRepository.createCustomerTable();

//            Customer customer = new Customer("Mahmoud", "Alzubi", "m7moudzo3bi@gmail.com");
//            customerRepository.save(customer);
//
//            List<Customer> customerList = customerRepository.findAll();
//            System.out.println(customerList);
//
//            Customer byId = customerRepository.findById(1);
//            System.out.println(byId);
        };
    }

}
