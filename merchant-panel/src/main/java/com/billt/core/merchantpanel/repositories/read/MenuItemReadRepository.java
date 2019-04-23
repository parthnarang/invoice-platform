package com.billt.core.merchantpanel.repositories.read;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.merchantpanel.Entities.CategoryEntity;
import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemReadRepository extends JpaRepository<MenuItemEntity, Long> {
    List<MenuItemEntity> findAllByMerchantAndCategoryEntity(@Param("merchant") Merchant merchant, @Param("categoryEntity") CategoryEntity categoryEntity);
    List<MenuItemEntity> findAllBymerchant(@Param("merchant") Merchant merchant);
}
