package com.billt.core.invoicereceiver.Service;

import com.billt.core.datasourcebase.entities.jpa.*;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    @Autowired
    MerchantReadRepository merchantReadRepository;


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


}
