package com.citra.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;
import com.citra.order.entity.Order;
import com.citra.order.repository.OrderRepository;
import com.citra.order.vo.ResponseTemplate;
import com.citra.order.vo.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    public OrderServiceImpl(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Order update(Long id, Order order) {

        Order existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setCustomerName(order.getCustomerName());
            existing.setProductName(order.getProductName());
            existing.setQuantity(order.getQuantity());
            existing.setPrice(order.getPrice());
            existing.setProductId(order.getProductId());

            return repository.save(existing);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ResponseTemplate> getOrderWithProdukById(Long id) {

        List<ResponseTemplate> responseList = new ArrayList<>();

        // ambil order
        Order order = getById(id);

        // ambil service PRODUK dari Eureka
        List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCT");

        if (instances.isEmpty()) {
            throw new RuntimeException("Service PRODUK tidak ditemukan di Eureka");
        }

        ServiceInstance serviceInstance = instances.get(0);

        // bentuk URL
        String url = serviceInstance.getUri().toString() + "/products/" + order.getProductId();

        System.out.println("CALL API PRODUK: " + url);

        // call API
        Product product;
        try {
            product = restTemplate.getForObject(url, Product.class);
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil data product: " + e.getMessage());
        }

        if (product == null) {
            throw new RuntimeException("Produk tidak ditemukan");
        }

        // mapping response
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduct(product);

        responseList.add(vo);

        return responseList;
    }
}