package com.billt.core.datasourcebase.Service.Impl;

import com.billt.core.datasourcebase.Service.IOrderService;
import com.billt.core.datasourcebase.entities.jpa.Order;
import com.billt.core.datasourcebase.repositories.jpa.read.OrderReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.OrderWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service("orderService")
public class IOrderImpl implements IOrderService {

    @Autowired
    OrderReadRepository orderReadRepository;

    @Autowired
    OrderWriteRepository orderWriteRepository;


    @Override
    public void registerOrder(String mid) {
        Order order = fetchOrder(mid);

        if(order == null){
            order.setCreatedOn(new Date());
        } else{
            long orderId = Long.parseLong(order.getOrderId());
            orderId = orderId + 1;
            order.setOrderId(String.valueOf(orderId));
            order.setUpdatedOn(new Date());
            System.out.println("Parth2201_:  " + order.toString());
            orderWriteRepository.save(order);
            return;
        }

        order.setUpdatedOn(new Date());
        System.out.println("Parth2201:  " + order.toString());
        orderWriteRepository.save(order);
    }

    @Override
    public Order fetchOrder(String mid) {
        Order order = null;
        if(mid != null){
            order = orderReadRepository.findOrderByMid(mid);
        }

        return order;
    }
}
