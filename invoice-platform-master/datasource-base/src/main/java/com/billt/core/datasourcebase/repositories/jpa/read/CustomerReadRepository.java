package com.billt.core.datasourcebase.repositories.jpa.read;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerReadRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByEmail(@Param("email") String email);
    Customer findCustomerByMobile(@Param("mobile") String mobile);
    Customer findCustomerByCid(@Param("cid") String cid);

}
