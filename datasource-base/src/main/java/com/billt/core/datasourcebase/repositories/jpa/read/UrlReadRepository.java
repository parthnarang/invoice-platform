package com.billt.core.datasourcebase.repositories.jpa.read;

import com.billt.core.datasourcebase.entities.jpa.InvoiceUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UrlReadRepository extends JpaRepository<InvoiceUrl, Long> {
    InvoiceUrl findTransactionByUrl(@Param("url") String url);
}
