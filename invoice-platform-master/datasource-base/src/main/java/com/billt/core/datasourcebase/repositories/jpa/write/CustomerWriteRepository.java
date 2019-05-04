package com.billt.core.datasourcebase.repositories.jpa.write;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerWriteRepository extends JpaRepository<Customer, Long> {
}
