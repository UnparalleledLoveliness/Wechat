package com.shopping.cloudShopping.order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

public class OrderController {

    @Resource
    OrderMapper orderMapper;

    @ResponseBody
    @RequestMapping("/addOrder")
    public void addUser(Order order) {
        orderMapper.addOrder(order);
    }

    @ResponseBody
    @RequestMapping("/getOrders")
    public List<Order> getOrders() {
        return orderMapper.getOrders();
    }

    @ResponseBody
    @RequestMapping("/getOrder")
    public Order getOrder(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @ResponseBody
    @RequestMapping("/updateOrder")
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }
}
