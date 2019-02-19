package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.entities.jpa.CustomerToken;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerTokenReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.CustomerTokenWriteRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.CustomerWriteRepository;
import com.billt.core.invoicereceiver.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("customerService")
public class ICustomerImpl implements ICustomerService {

    @Autowired
    CustomerReadRepository customerReadRepository;

    @Autowired
    CustomerWriteRepository customerWriteRepository;

    @Autowired
    CustomerTokenReadRepository customerTokenReadRepository;

    @Autowired
    CustomerTokenWriteRepository customerTokenWriteRepository;

    public void saveCustomer(Customer customer){

        customerWriteRepository.save(customer);
    }

    /*
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
    }*/

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




}


