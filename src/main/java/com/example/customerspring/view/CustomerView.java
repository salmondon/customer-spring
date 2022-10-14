package com.example.customerspring.view;

import com.example.customerspring.modal.Customer;
import com.example.customerspring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CustomerView {
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/")
    public String Home(Model model) {
        ModelId modelId = new ModelId();
        model.addAttribute("modelId", modelId);
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    @GetMapping("/addNewCustomer")
    public String addNewCustomer(Model model) {
        Customer c = new Customer();
        model.addAttribute("customer", c);
        return "add_customer";
    }

    @PostMapping("/saveNewCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customerRequest) {
        customerRepository.save(customerRequest);
        return "redirect:/";
    }

    @GetMapping("/getCustomerId")
    public String findCustomerById(Model model, @ModelAttribute(value = "customerId")ModelId modelId) {
        Optional<Customer> customer = customerRepository.findById(Long.parseLong(modelId.getId()));
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "customer_id";
        } else {
            return "/redirect:/";
        }
    }
}
