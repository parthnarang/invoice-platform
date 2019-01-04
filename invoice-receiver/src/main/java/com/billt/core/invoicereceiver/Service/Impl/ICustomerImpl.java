package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerReadRepository;
import com.billt.core.invoicereceiver.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class ICustomerImpl implements ICustomerService {

    @Autowired
    CustomerReadRepository customerReadRepository;

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


}


