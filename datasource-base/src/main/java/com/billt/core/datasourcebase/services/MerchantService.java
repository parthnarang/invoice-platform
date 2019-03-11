package com.billt.core.datasourcebase.services;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    private static final Logger log = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    MerchantReadRepository merchantReadRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Boolean findMerchantByMid(String mid){

        Merchant merchant = merchantReadRepository.findAllByMid(mid);

        if(merchant == null){
          return false;
        } else {
            return true;
        }
    }

    public String findMerchantLogoByMid(String mid){
        Merchant merchant = merchantReadRepository.findAllByMid(mid);
        if(merchant == null){
            return "NULL";
        } else{
            return merchant.getMerchantLogo();
        }
    }

    public void addNewMerchant(Merchant merchant) {
        merchant.setPassword(bCryptPasswordEncoder.encode(merchant.getPassword()));
            merchantReadRepository.save(merchant);
    }


}
