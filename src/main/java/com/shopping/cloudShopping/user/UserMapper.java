package com.shopping.cloudShopping.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByUsername(String username);

    User getUserById(Integer id);

    void addUser(User user);

    void updateUser(User user);
}
