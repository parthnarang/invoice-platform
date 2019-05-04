package com.billt.core.merchantpanel.service.Impl;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.merchantpanel.Entities.CategoryEntity;
import com.billt.core.merchantpanel.repositories.read.MenuCategoryReadRepository;
import com.billt.core.merchantpanel.repositories.read.MenuItemReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryService {

    @Autowired
    MenuCategoryReadRepository menuCategoryReadRepository;

    public List<CategoryEntity> findCategoriesByMerchant(Merchant merchant) {

        if (merchant == null) {
            return null;
        } else {
            return menuCategoryReadRepository.findAllByMerchant(merchant);
        }
    }
}
