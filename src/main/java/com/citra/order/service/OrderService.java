package com.citra.order.service;

import java.util.List;
import com.citra.order.entity.Order;

public interface OrderService {

    Order create(Order order);

    List<Order> getAll();

    Order getById(Long id);

    Order update(Long id, Order order);

    void delete(Long id);
}