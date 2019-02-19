package com.billt.core.datasourcebase.repositories.jpa.read;

import com.billt.core.datasourcebase.entities.jpa.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OrderReadRepository extends JpaRepository<Order, Long> {
    Order findOrderByMid(@Param("mid") String mid);
}
