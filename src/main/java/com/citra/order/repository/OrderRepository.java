package com.citra.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.citra.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}