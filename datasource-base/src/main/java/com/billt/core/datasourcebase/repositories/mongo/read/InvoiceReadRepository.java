package com.billt.core.datasourcebase.repositories.mongo.read;

import com.billt.core.datasourcebase.collection.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceReadRepository extends MongoRepository<Invoice,String> {

}
