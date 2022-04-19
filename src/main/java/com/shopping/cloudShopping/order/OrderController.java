package com.shopping.cloudShopping.order;

import com.shopping.cloudShopping.ReturnResult;
import com.shopping.cloudShopping.food.Food;
import com.shopping.cloudShopping.food.FoodMapper;
import com.shopping.cloudShopping.user.User;
import com.shopping.cloudShopping.user.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Resource
    OrderMapper orderMapper;

    @Resource
    FoodMapper foodMapper;

    @Resource
    UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/addOrder")
    public void addUser(@RequestBody Order order) {
        order.setOrderTime(new Date());
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

    @ResponseBody
    @RequestMapping("/addOrder2Shop")
    public void addOrder2Shop(Integer id, Integer userId) {
        Food foodById = foodMapper.getFoodById(id);
        Order order = new Order();
        order.setStatus("0");
        order.setName(foodById.getName());
        order.setPrice(foodById.getPrice());
        order.setFoodId(foodById.getId());
        order.setUserId(userId);
        order.setOrderTime(new Date());
        orderMapper.addOrder(order);
    }

    @ResponseBody
    @RequestMapping("/getOrderByUserId")
    public List<Order> getOrderByUserId(Integer userId) {
        return orderMapper.getOrderByIUserId(userId);
    }

    @ResponseBody
    @RequestMapping("/getOrderByShopId")
    public List<Order> getOrderByShopId(Integer id) {
        System.out.println(id);
        return orderMapper.getOrderByShopId(id);
    }

    @ResponseBody
    @RequestMapping("/deleteOrder")
    public ReturnResult deleteOrder(Integer id) {
        orderMapper.deleteOrder(id);
        return new ReturnResult(ReturnResult.SUCCESS, "删除成功");
    }

    @ResponseBody
    @RequestMapping("/getAllPrice")
    public Double getAllPrice(Integer userId) {
        Double allPrice = 0.0;
        List<Order> orders = orderMapper.getOrderByIUserId(userId);
        for (Order order : orders) {
            allPrice += (order.getPrice() == null ? 0 : order.getPrice());
        }
        return allPrice;
    }

    @ResponseBody
    @RequestMapping("/getAllScore")
    public Integer getAllScore(Integer userId) {
        Integer allScore = 0;
        List<Order> orders = orderMapper.getOrderByIUserId(userId);
        for (Order order : orders) {
            Integer score = foodMapper.getFoodById(order.getFoodId()).getScore();
            allScore += score == null ? 0 : score;
        }
        return allScore;
    }

    @ResponseBody
    @RequestMapping("/buy")
    public ReturnResult buy(Integer userId, Integer allScore) {
        List<Order> orders = orderMapper.getOrderByIUserId(userId);
        User user = userMapper.getUserById(userId);
        user.setScore(user.getScore() + allScore);
        userMapper.updateUser(user);
        for (Order order : orders) {
            Food food = foodMapper.getFoodById(order.getFoodId());
            food.setNumber((food.getNumber() == null ? 0 : food.getNumber()) + 1);
            order.setStatus("1");
            orderMapper.updateOrder(order);
        }
        return new ReturnResult(ReturnResult.SUCCESS, "下单成功");
    }

    @ResponseBody
    @RequestMapping("/cancel")
    public ReturnResult cancel(Integer userId) {
        List<Order> orders = orderMapper.getOrderByIUserId(userId);
        for (Order order : orders) {
            order.setStatus("2");
            orderMapper.updateOrder(order);
        }
        return new ReturnResult(ReturnResult.SUCCESS, "全部取消成功");
    }

    @ResponseBody
    @RequestMapping("/getHisOrderByUserId")
    public List<Order> getHisOrderByUserId(Integer userId) {
        return orderMapper.getHisOrderByUserId(userId);
    }

    @ResponseBody
    @RequestMapping("/getHisOrderByShopId")
    public List<Order> getHisOrderByShopId(Integer userId) {
        return orderMapper.getHisOrderByShopId(userId);
    }

    @ResponseBody
    @RequestMapping("/endOrder")
    public ReturnResult endOrder(Integer id) {
        Order order = orderMapper.getOrderById(id);
        order.setStatus("3");
        orderMapper.updateOrder(order);
        return new ReturnResult(ReturnResult.SUCCESS, "该订单已完成");
    }

    @ResponseBody
    @RequestMapping("/deleteHis")
    public ReturnResult deleteHis(Integer userId) {
        List<Order> orders = orderMapper.getHisOrderByUserId(userId);
        for (Order order : orders) {
            orderMapper.deleteOrder(order.getId());
        }
        return new ReturnResult(ReturnResult.SUCCESS, "全部删除成功");
    }
}
