package com.billt.core.datasourcebase.repositories.mongo.write;

import com.billt.core.datasourcebase.collection.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InvoiceWriteRepository extends MongoRepository<Invoice,String> {

}
