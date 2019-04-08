package com.billt.core.merchantpanel.repositories.write;

import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemWriteRepository extends JpaRepository<MenuItemEntity, Long> {
}
