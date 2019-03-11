package com.billt.core.merchantpanel.service.Impl;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.merchantpanel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements IUserService {

    @Autowired
    private MerchantReadRepository merchantReadRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Merchant merchant) {
        merchant.setPassword(bCryptPasswordEncoder.encode(merchant.getPassword()));
        merchantReadRepository.save(merchant);
    }

    @Override
    public Merchant findByUsername(String username) {
        return merchantReadRepository.findByEmail(username);
    }
}
