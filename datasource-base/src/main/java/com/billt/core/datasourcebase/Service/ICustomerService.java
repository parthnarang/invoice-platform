package com.billt.core.datasourcebase.Service;

import com.billt.core.datasourcebase.entities.jpa.Customer;

public interface ICustomerService {
    public Customer fetchCustomerByEmail(String custEmail);
    public Customer fetchCustomerByCid(String custId);
    public Customer fetchCustomerByMobile(String custMobile);
}
