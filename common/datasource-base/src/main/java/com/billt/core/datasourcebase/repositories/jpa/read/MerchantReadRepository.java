package com.billt.core.datasourcebase.repositories.jpa.read;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.entities.jpa.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MerchantReadRepository extends JpaRepository<Merchant, Long> {

    List<Merchant> findAllBy(@Param("vendor") Vendor vendor);

}

