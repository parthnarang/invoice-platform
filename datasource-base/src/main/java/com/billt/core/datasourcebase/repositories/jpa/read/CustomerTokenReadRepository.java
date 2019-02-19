package com.billt.core.datasourcebase.repositories.jpa.read;


import com.billt.core.datasourcebase.entities.jpa.CustomerToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerTokenReadRepository extends JpaRepository<CustomerToken, Long> {
    CustomerToken findCustomerTokenByCid(@Param("cid") String cid);
}
