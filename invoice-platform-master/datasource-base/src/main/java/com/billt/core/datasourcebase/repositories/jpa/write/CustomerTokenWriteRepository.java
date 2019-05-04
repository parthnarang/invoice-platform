package com.billt.core.datasourcebase.repositories.jpa.write;

import com.billt.core.datasourcebase.entities.jpa.CustomerToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTokenWriteRepository extends JpaRepository<CustomerToken, Long> {
}
