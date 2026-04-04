package com.citra.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.citra.order.entity.Order;
import com.citra.order.service.OrderService;
import com.citra.order.vo.ResponseTemplate;
import com.citra.order.vo.Product;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Order create(@RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order order) {
        return service.update(id, order);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Order deleted";
    }

    @GetMapping("/products/{id}")
    public List<ResponseTemplate> getOrderWithProdukById(@PathVariable Long id) {
        return service.getOrderWithProdukById(id);
    }
}