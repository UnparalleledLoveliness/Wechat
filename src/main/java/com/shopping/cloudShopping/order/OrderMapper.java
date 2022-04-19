package com.shopping.cloudShopping.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Order getOrderById(Integer id);

    Order getOrderByFoodId(Integer id);

    List<Order> getOrders();

    void updateOrder(Order order);

    void addOrder(Order order);

    List<Order> getOrderByIUserId(Integer userId);

    List<Order> getHisOrderByUserId(Integer userId);

    void deleteOrder(Integer id);

    List<Order> getOrderByShopId(Integer id);

    List<Order> getHisOrderByShopId(Integer userId);
}
