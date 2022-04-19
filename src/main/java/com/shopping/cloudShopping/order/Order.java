package com.shopping.cloudShopping.order;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    Integer id;
    Integer userId;
    String name;
    Double price;
    Integer number;
    Date orderTime;
    // 0 待处理，1 已付款，2 已取消,3 已完成
    String status;
    String type;
    Integer foodId;
    Integer shopId;
}
