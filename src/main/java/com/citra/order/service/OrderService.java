package com.citra.order.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import com.citra.order.entity.Order;
import com.citra.order.vo.ResponseTemplate;
import com.citra.order.vo.Product;

public interface OrderService {

    Order create(Order order);

    List<Order> getAll();

    Order getById(Long id);

    Order update(Long id, Order order);

    void delete(Long id);
    List<ResponseTemplate> getOrderWithProdukById(Long id);
}