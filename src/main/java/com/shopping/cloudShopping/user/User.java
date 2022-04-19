package com.shopping.cloudShopping.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Date logDate;
    private String status;
    private Integer score;
    //0 普通用户，1 普通商家，2 vip,3 超级用户
    private String type;
    private String phone;
    private String code;
    private Integer level;
    private String message;
}
