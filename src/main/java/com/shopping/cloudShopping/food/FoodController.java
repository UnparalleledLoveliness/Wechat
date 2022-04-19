package com.shopping.cloudShopping.food;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class FoodController {
    @Resource
    FoodMapper foodMapper;

    @ResponseBody
    @RequestMapping("/getFoodByShop")
    public List<Food> getFoodByShop(Integer id) {
        return foodMapper.getFoodByShop(id);
    }

    @ResponseBody
    @RequestMapping("/getFoodById")
    public Food getFoodById(Integer id) {
        return foodMapper.getFoodById(id);
    }

    @ResponseBody
    @RequestMapping("/deleteFood")
    public void deleteFood(Integer id) {
        foodMapper.deleteFood(id);
    }

    @ResponseBody
    @RequestMapping("/addFood")
    public void addFood(@RequestBody Food food) {
        food.setTime(new Date());
        food.setNumber(0);
        foodMapper.addFood(food);
    }
}
