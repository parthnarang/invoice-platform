package com.billt.core.datasourcebase.repositories.mongo.read;

import com.billt.core.datasourcebase.collection.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface InvoiceReadRepository extends MongoRepository<Invoice,String> {

    public List<Invoice> findByEmail(@RequestParam("email") String email);
    public List<Invoice> findByCid(String cid);
    public Invoice findByTransID(String transID);
    public List<Invoice> findByDateBetweenAndMid(Date from, Date to, String mid);
}
