package com.shopping.cloudShopping.food;

import lombok.Data;

import java.util.Date;

@Data
public class Food {
    private Integer id;
    private Integer shopId;
    private String message;
    private String url;
    private Integer score;
    private Date time;
    private Double price;
    private String name;
    private Integer number;
    private String type;
}
