package com.billt.core.invoicereceiver.Service;

import com.billt.core.datasourcebase.entities.jpa.Customer;

public interface ICustomerService {

    public Customer checkIfCustomerExist(String mobile, String email);
}
