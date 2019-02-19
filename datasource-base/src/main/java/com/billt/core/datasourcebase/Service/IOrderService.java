package com.billt.core.datasourcebase.Service;

import com.billt.core.datasourcebase.entities.jpa.Order;

public interface IOrderService {
    public void registerOrder(String mid);
    public Order fetchOrder(String mid);
}
