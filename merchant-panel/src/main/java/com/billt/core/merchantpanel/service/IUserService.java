package com.billt.core.merchantpanel.service;

import com.billt.core.datasourcebase.entities.jpa.Merchant;

public interface IUserService {

    void save(Merchant merchant);

    Merchant findByUsername(String username);
}
