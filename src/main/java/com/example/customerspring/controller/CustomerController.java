package com.example.customerspring.controller;

import com.example.customerspring.modal.Customer;
import com.example.customerspring.repository.CustomerRepository;
import com.example.customerspring.view.ModelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @ResponseBody
    @GetMapping("/customers/get")
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
