package com.billt.core.invoicereceiver.service;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.invoicereceiver.Model.RegistrationRequestBody;
import com.billt.core.invoicereceiver.Model.RegistrationRequestHeader;

import java.util.List;

public interface ICustomerService {

    public Customer checkIfCustomerExist(String mobile, String email);
    public Customer checkIfCustomerExist(String custId);
    public void saveCustomer(Customer customer);

    List<Merchant> findMerchantDetails(RegistrationRequestHeader header, RegistrationRequestBody body);
}
