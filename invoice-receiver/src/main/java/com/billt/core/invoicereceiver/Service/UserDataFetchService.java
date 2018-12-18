package com.billt.core.invoicereceiver.Service;

import com.billt.core.invoicereceiver.Model.Invoice;
import com.billt.core.invoicereceiver.Repositories.InvoiceRepository;
import com.billt.core.invoicereceiver.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataFetchService {
    @Autowired
    InvoiceRepository invoiceRepository;

    public List<Invoice> fetchInvoice(String id){
    List<Invoice> invoices = invoiceRepository.findByCustomerId(id);
    System.out.println("check");
    return invoices;
    }

}
