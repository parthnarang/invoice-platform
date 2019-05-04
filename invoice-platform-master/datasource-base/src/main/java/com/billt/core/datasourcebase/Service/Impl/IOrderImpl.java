package com.billt.core.datasourcebase.Service.Impl;

import com.billt.core.datasourcebase.Service.IOrderService;
import com.billt.core.datasourcebase.entities.jpa.Orders;
import com.billt.core.datasourcebase.repositories.jpa.read.OrderReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.OrderWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service("orderService")
public class IOrderImpl implements IOrderService {

    @Autowired
    OrderReadRepository orderReadRepository;

    @Autowired
    OrderWriteRepository orderWriteRepository;

    @Override
    public void registerOrder(Orders orders) {
        System.out.println("Parth2201:  " + orders.toString());
        orderWriteRepository.save(orders);
    }
/*
    @Override
    public void registerOrder(String mid) {
        Orders orders = fetchOrder(mid);

        if(orders == null){
            orders.setCreatedOn(new Date());
        } else{
            long orderId = Long.parseLong(orders.getOrderId());
            orderId = orderId + 1;
            orders.setOrderId(String.valueOf(orderId));
            orders.setUpdatedOn(new Date());
            System.out.println("Parth2201_:  " + orders.toString());
            orderWriteRepository.save(orders);
            return;
        }

        orders.setUpdatedOn(new Date());
        System.out.println("Parth2201:  " + orders.toString());
        orderWriteRepository.save(orders);
    }
*/
    @Override
    public Orders fetchOrder(String mid) {
        Orders orders = null;
        if(mid != null){
            orders = orderReadRepository.findOrderByMid(mid);
        }

        return orders;
    }
}
