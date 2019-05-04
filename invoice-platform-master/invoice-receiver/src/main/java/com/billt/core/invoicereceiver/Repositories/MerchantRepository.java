package com.billt.core.invoicereceiver.Repositories;


import com.billt.core.datasourcebase.entities.jpa.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantRepository  extends MongoRepository<Merchant,String> {
// List<Invoice> findByCustomerId(String id);
}