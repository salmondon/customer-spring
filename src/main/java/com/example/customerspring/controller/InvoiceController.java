package com.example.customerspring.controller;

import com.example.customerspring.modal.Customer;
import com.example.customerspring.modal.Invoice;
import com.example.customerspring.repository.CustomerRepository;
import com.example.customerspring.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired private InvoiceRepository invoiceRepository;
    @Autowired private CustomerRepository customerRepository;

    @GetMapping(path = "/invoices/get")
    public Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping(path = "invoices/{id}/get")
    public ResponseEntity<Invoice> getById(@PathVariable(value = "id") long id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/customers/{id}/invoice/add")
    public ResponseEntity<Invoice> addNewInvoice(@PathVariable(value = "id") long id, @RequestBody Invoice invoiceRequest) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            invoiceRequest.setCustomer(optional.get());
            invoiceRequest.setDate(LocalDate.now().toString());
            invoiceRepository.save(invoiceRequest);
            return new ResponseEntity<>(invoiceRequest, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/invoices/{id}/delete")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {
        invoiceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
