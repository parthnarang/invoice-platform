package com.billt.core.merchantpanel.service;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerTokenReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.invoicereceiver.Model.RegistrationRequestBody;
import com.billt.core.invoicereceiver.Model.RegistrationRequestHeader;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MerchantService {

    private static final Logger log = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    MerchantReadRepository merchantReadRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomerTokenReadRepository customerTokenReadRepository;


    public Boolean findMerchantByMid(String mid){

        Merchant merchant = merchantReadRepository.findAllByMid(mid);

        if(merchant == null){
          return false;
        } else {
            return true;
        }
    }

    public Merchant findByEmail(String email){

        Merchant merchant = merchantReadRepository.findByEmail(email);

        return merchant;
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
        merchant.setCreatedOn(new Date());
        merchant.setUpdatedOn(new Date());
            merchantReadRepository.save(merchant);
    }

    public Merchant findByConfirmationToken(String confirmationToken) {
        return merchantReadRepository.findByConfirmationToken(confirmationToken);
    }

    public Merchant findMerchantDetails(RegistrationRequestHeader header, RegistrationRequestBody body){
        FirebaseToken decodedToken = null;
        String idToken = header.getToken();
        try {
            //decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            //String uid = decodedToken.getUid();
            String uid = "123";
            if(uid!=null){

            }
            else{
                return null;
            }

        } catch (/*FirebaseAuth*/Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
