package com.basic.myspringboot.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void notFound() {
        Optional<Customer> notExistOptional = customerRepository.findByCustomerId("B001");
        assertThat(notExistOptional).isEmpty();

    }

    @Test @Rollback(value = false)
    void crud() {
        //System.out.println(customerRepository.getClass().getName());
        Customer customer = new Customer();
        customer.setCustomerId("A001");
        customer.setCustomerName("스프링");
        //등록
        Customer addCustomer = customerRepository.save(customer);
        System.out.println(addCustomer.getCustomerId() + " " + addCustomer.getCustomerName());
        assertThat(addCustomer).isNotNull();
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링");

        assertEquals("A001", addCustomer.getCustomerId());

        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(addCustomer.getCustomerId());
        assertThat(optionalCustomer).isNotEmpty();
        Customer existCustomer = optionalCustomer.get();
        //update - setter method 호출 dirty checking
        existCustomer.setCustomerName("SpringBoot");
        assertThat(existCustomer.getCustomerName()).isEqualTo("SpringBoot");
    }
}