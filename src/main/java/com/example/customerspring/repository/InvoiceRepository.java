package com.example.customerspring.repository;

import com.example.customerspring.modal.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
