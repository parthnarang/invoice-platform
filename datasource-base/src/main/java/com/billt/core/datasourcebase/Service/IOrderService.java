package com.billt.core.datasourcebase.Service;

import com.billt.core.datasourcebase.entities.jpa.Orders;

public interface IOrderService {
    public void registerOrder(Orders orders);
    public Orders fetchOrder(String mid);
}
