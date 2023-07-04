package com.unity.multi.sources.invoicing.repository;

import com.unity.multi.sources.invoicing.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}