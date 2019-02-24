package com.billt.core.merchantpanel.repositories.read;

import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemReadRepository extends JpaRepository<MenuItemEntity, Long> {
}
