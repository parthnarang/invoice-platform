package com.billt.core.datasourcebase.repositories.jpa.write;

import com.billt.core.datasourcebase.entities.jpa.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderWriteRepository extends JpaRepository<Order, Long> {
}
