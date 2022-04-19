package com.shopping.cloudShopping.comment;

import com.shopping.cloudShopping.user.User;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private Integer foodId;
    private Date time;
    private Integer userId;
    private User user;
    private String content;
}
