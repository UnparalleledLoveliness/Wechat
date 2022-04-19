package com.shopping.cloudShopping.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByUsername(String username);

    User getUserById(Integer id);

    void addUser(User user);

    void updateUser(User user);

    User getUserByPhone(String phone);

    List<User> getShops();
}
