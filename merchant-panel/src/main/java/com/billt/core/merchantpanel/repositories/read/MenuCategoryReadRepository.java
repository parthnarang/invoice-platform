package com.billt.core.merchantpanel.repositories.read;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.merchantpanel.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuCategoryReadRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAllByMerchant(@Param("merchant") Merchant merchant);
    CategoryEntity findFirstByMerchantIdAndCategoryname(@Param("merchantId") long merchantId, @Param("categoryname") String categoryname);



}
