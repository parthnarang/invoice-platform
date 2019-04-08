package com.billt.core.datasourcebase.repositories.jpa.read;

import com.billt.core.datasourcebase.entities.jpa.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OrderReadRepository extends JpaRepository<Orders, Long> {
    Orders findOrderByMid(@Param("mid") String mid);
}
