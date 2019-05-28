package com.billt.core.invoicereceiver.service.Impl;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.CustomerWriteRepository;
import com.billt.core.invoicereceiver.Model.RegistrationRequestBody;
import com.billt.core.invoicereceiver.Model.RegistrationRequestHeader;
import com.billt.core.invoicereceiver.service.ICustomerService;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class ICustomerImpl implements ICustomerService {

    @Autowired
    CustomerReadRepository customerReadRepository;

    @Autowired
    CustomerWriteRepository customerWriteRepository;

    @Autowired
    MerchantReadRepository merchantReadRepository;

    public void saveCustomer(Customer customer){

        customerWriteRepository.save(customer);
    }

    public Customer checkIfCustomerExist(String mobile, String email){

        Customer customer = null;

        if(mobile != null){
            customer = customerReadRepository.findCustomerByMobile(mobile);
        }

        if(customer == null && email != null){
            customer = customerReadRepository.findCustomerByEmail(email);
        }

        return customer;
    }

    public Customer checkIfCustomerExist(String custId){

        Customer customer = null;

        if(custId != null){
            customer = customerReadRepository.findCustomerByCid(custId);
        }


        return customer;
    }

    public List<Merchant> findMerchantDetails(RegistrationRequestHeader header, RegistrationRequestBody body){
        FirebaseToken decodedToken = null;
        String idToken = header.getToken();
        try {
            //decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            //String uid = decodedToken.getUid();
            String uid = "123";
            if(uid!=null){
                List<Merchant> allMerchants = merchantReadRepository.findAll();
                return allMerchants;
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


