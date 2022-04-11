package com.shopping.cloudShopping.order;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    Integer id;
    Integer userId;
    String name;
    Integer price;
    Integer number;
    Date orderTime;
    String status;
    String type;
}
