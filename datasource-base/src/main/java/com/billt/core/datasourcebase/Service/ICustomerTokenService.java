package com.billt.core.datasourcebase.Service;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.entities.jpa.CustomerToken;

public interface ICustomerTokenService {
    public void registerCustomerToken(CustomerToken customerToken);
    public CustomerToken fetchCustomerToken(String custId);
}
