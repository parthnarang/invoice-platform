package com.billt.core.invoicereceiver.Repositories;

import com.billt.core.invoicereceiver.Model.Invoice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvoiceRepository  extends MongoRepository<Invoice,String> {
    List<Invoice> findByCustomerId(String id);
}
