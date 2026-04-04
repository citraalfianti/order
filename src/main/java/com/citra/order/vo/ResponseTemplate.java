package com.citra.order.vo;
import com.citra.order.entity.Order;
import lombok.Data;

@Data
public class ResponseTemplate{
    Order order;
    Product product;

     public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}