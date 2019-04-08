package com.billt.core.datasourcebase.Service.Impl;

import com.billt.core.datasourcebase.Service.ICustomerService;
import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerReadRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ICustomerImpl implements ICustomerService {

    @Autowired
    CustomerReadRepository customerReadRepository;

    @Override
    public Customer fetchCustomerByEmail(String custEmail) {
        Customer customer = null;
        if(custEmail != null){
            customer = customerReadRepository.findCustomerByEmail(custEmail);
        }

        return customer;
    }

    @Override
    public Customer fetchCustomerByCid(String custId) {
        Customer customer = null;
        if(custId != null){
            customer = customerReadRepository.findCustomerByCid(custId);
        }
        return customer;
    }

    @Override
    public Customer fetchCustomerByMobile(String custMobile) {
        Customer customer = null;
        if(custMobile != null){
            customer = customerReadRepository.findCustomerByMobile(custMobile);
        }

        return customer;
    }
}
