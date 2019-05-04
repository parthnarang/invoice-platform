package com.billt.core.datasourcebase.repositories.jpa.write;

import com.billt.core.datasourcebase.entities.jpa.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderWriteRepository extends JpaRepository<Orders, Long> {
}
