package com.billt.core.datasourcebase.repositories.jpa.write;

import com.billt.core.datasourcebase.entities.jpa.InvoiceUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlWriteRepository extends JpaRepository<InvoiceUrl, Long> {
}
