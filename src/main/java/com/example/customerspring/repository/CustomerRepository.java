package com.example.customerspring.repository;

import com.example.customerspring.modal.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
