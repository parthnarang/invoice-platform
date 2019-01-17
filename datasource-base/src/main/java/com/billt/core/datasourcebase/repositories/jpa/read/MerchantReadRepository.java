package com.billt.core.datasourcebase.repositories.jpa.read;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface MerchantReadRepository extends JpaRepository<Merchant, String> {

    Merchant findAllByMid(@Param("mid") String mid);

}

