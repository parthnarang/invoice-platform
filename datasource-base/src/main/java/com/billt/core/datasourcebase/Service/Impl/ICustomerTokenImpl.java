package com.billt.core.datasourcebase.Service.Impl;

import com.billt.core.datasourcebase.Service.ICustomerTokenService;
import com.billt.core.datasourcebase.entities.jpa.CustomerToken;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerTokenReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.CustomerTokenWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("customerTokenService")
public class ICustomerTokenImpl implements ICustomerTokenService {

    @Autowired
    CustomerTokenReadRepository customerTokenReadRepository;

    @Autowired
    CustomerTokenWriteRepository customerTokenWriteRepository;

    @Override
    public void registerCustomerToken(CustomerToken customerToken) {

        CustomerToken customerToken1 = fetchCustomerToken(customerToken.getCid());

        if(customerToken1 == null){
            customerToken.setCreatedOn(new Date());
        } else{
            customerToken1.setToken(customerToken.getToken());
            customerToken1.setUpdatedOn(new Date());
            System.out.println("Parth2201_:  "+customerToken1.toString());
            customerTokenWriteRepository.save(customerToken1);
            return;
        }

        customerToken.setUpdatedOn(new Date());
        System.out.println("Parth2201:  "+customerToken.toString());
        customerTokenWriteRepository.save(customerToken);
    }

    @Override
    public CustomerToken fetchCustomerToken(String custId){
        CustomerToken customerToken = null;
        if(custId != null){
            customerToken = customerTokenReadRepository.findCustomerTokenByCid(custId);
        }

        return customerToken;
    }
}


