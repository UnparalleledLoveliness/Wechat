package com.shopping.cloudShopping.food;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMapper {
    List<Food> getFoodByShop(Integer id);
    Food getFoodById(Integer id);
    void deleteFood(Integer id);
    void addFood(Food food);
}
