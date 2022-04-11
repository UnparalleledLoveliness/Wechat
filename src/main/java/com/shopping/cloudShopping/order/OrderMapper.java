package com.shopping.cloudShopping.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Order getOrderById(Integer id);
    List<Order> getOrders();
    void updateOrder(Order order);
    void addOrder(Order order);
}
