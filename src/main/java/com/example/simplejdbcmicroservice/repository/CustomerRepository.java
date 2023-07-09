package com.example.simplejdbcmicroservice.repository;

import com.example.simplejdbcmicroservice.mapper.CustomerMapper;
import com.example.simplejdbcmicroservice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;


@Repository
public class CustomerRepository {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final JdbcTemplate jdbcTemplate;


    ///////////////////////////////////////////////////////

    /**
     * Constructor
     *
     * @param jdbcTemplate
     */
    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    ///////////////////////////////////////////////////////

    /**
     * To Save New Customer Record
     *
     * @param customer
     */
    @Transactional
    public void save(Customer customer) {
        try {
            logger.debug("save({})", customer);
            String sql = "INSERT INTO customers (first_name, last_name, email) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail());
        } finally {
            logger.debug("/save({})", customer);
        }
    }

    ///////////////////////////////////////////////////////

    /**
     * To Find Customer Based On ID
     *
     * @param id
     * @return
     */
    public Customer findById(long id) {
        try {
            logger.debug("findById()", id);
            String sql = """ 
                    SELECT id, first_name, last_name, email
                    FROM customers
                    WHERE id = ? 
                      """;
            Customer customer = jdbcTemplate.queryForObject(sql, new CustomerMapper(), id);
            return customer;
        } finally {
            logger.debug("/findById()", id);
        }
    }

    ///////////////////////////////////////////////////////

    /**
     * To Retrieve All Customers Records
     *
     * @return
     */
    public List<Customer> findAll() {
        try {
            logger.debug("findAll()");
            String sql = """
                    SELECT id, first_name, last_name, email
                    FROM customers
                    """;
            List<Customer> customersList = jdbcTemplate.query(sql, new CustomerMapper());
            return customersList;
        } finally {
            logger.debug("/findAll()");
        }
    }

    ///////////////////////////////////////////////////////

    /**
     * To Update Customer Record Based On ID
     *
     * @param id
     * @param customer
     */
    @Transactional
    public void update(long id, Customer customer) {
        try {
            logger.debug("update({},{})", id, customer);
            String sql = """
                    UPDATE customers
                         SET first_name = ?,    
                         last_name = ?,
                         email = ?
                         WHERE id = ?
                    """;
            jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), id);
        } finally {
            logger.debug("/update({},{})", id, customer);
        }
    }

    ///////////////////////////////////////////////////////

    /**
     * To Delete Customer Record Based on ID
     *
     * @param id
     */
    @Transactional
    public void delete(long id) {
        try {
            logger.debug("delete({})", id);
            String sql = """
                    DELETE FROM customers
                    WHERE id = ?
                    """;
            jdbcTemplate.update(sql, id);
        } finally {
            logger.debug("/delete({})", id);
        }
    }

    ///////////////////////////////////////////////////////

    /**
     * To Create Customer Table in H2 DB
     */
    @Transactional
    public void createCustomerTable() {
        try {
            logger.info("createCustomerTable()");
            String sql = """ 
                    CREATE TABLE IF NOT EXISTS customers ( 
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    first_name VARCHAR(50),
                    last_name VARCHAR(50),
                    email VARCHAR(50) 
                    )
                     """;
            jdbcTemplate.execute(sql);
        } finally {
            logger.info("/createCustomerTable()");
        }
    }


    ///////////////////////////////////////////////////////

    /**
     * To Drop Table If Exists
     */
    @Transactional
    public void dropCustomerTableIfExists() {
        try {
            logger.info("dropCustomerTableIfExists()");
            String sql = "DROP TABLE customers IF EXISTS";
            jdbcTemplate.execute(sql);
        } finally {
            logger.info("/dropCustomerTableIfExists()");
        }
    }
}
