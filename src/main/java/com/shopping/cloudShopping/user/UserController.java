package com.shopping.cloudShopping.user;

import com.shopping.cloudShopping.ReturnResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.shopping.cloudShopping.smsCode.Sample.sendSms;

@Controller
public class UserController {
    @Resource
    UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/getUserById")
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        if ("0".equals(user.getType()))
            user.setType("普通用户");
        else if ("1".equals(user.getType())) {
            user.setType("普通商家");
        } else if ("2".equals(user.getType()))
            user.setType("VIP用户");
        else if ("3".equals(user.getType()))
            user.setType("超级用户");
        return user;
    }

    @ResponseBody
    @RequestMapping("/getUserByPhone")
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @ResponseBody
    @RequestMapping("/getShops")
    public List<User> getShops() {
        return userMapper.getShops();
    }

    @ResponseBody
    @RequestMapping("/getUserByName")
    public User getUserByName(String name) {
        return userMapper.getUserByUsername(name);
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public void addUser(User user) {
        user.setLogDate(new Date());
        userMapper.addUser(user);
    }

    @ResponseBody
    @RequestMapping("/login")
    public ReturnResult login(Integer id, String password) {
        if (id == null || password == null)
            return new ReturnResult(ReturnResult.FAIL, "用户名或者密码为空");
        User userById = userMapper.getUserById(id);
        if (!userById.getPassword().equals(password))
            return new ReturnResult(ReturnResult.FAIL, "用户名或者密码错误");
        return new ReturnResult(ReturnResult.SUCCESS, "登录成功", userById);
    }

    @ResponseBody
    @RequestMapping("/resetPwd")
    public ReturnResult resetPwd(Integer id) {
        User userById = userMapper.getUserById(id);
        userById.setPassword("123");
        userMapper.updateUser(userById);
        return new ReturnResult(ReturnResult.SUCCESS, "重置密码成功,新密码为123");
    }

    @ResponseBody
    @RequestMapping("/register")
    public ReturnResult register(User user) {
        if ("1".equals(user.getType())) {
            if (user.getCode() == null || !user.getCode().equals(getCode()))
                return new ReturnResult(ReturnResult.FAIL, "邀请码无效");
        }
        user.setLogDate(new Date());
        userMapper.addUser(user);
        return new ReturnResult(ReturnResult.SUCCESS, "注册成功", user);
    }

    @ResponseBody
    @RequestMapping("/resetCode")
    public ReturnResult resetCode(Integer id) {
        User user = userMapper.getUserById(id);
        user.setCode(getRandomString(10));
        return new ReturnResult(ReturnResult.SUCCESS, "随机重置成功", user.getCode());
    }

    @ResponseBody
    @RequestMapping("/changeMessage")
    public ReturnResult changeMessage(Integer id, String message) {
        User user = userMapper.getUserById(id);
        user.setMessage(message);
        userMapper.updateUser(user);
        return new ReturnResult(ReturnResult.SUCCESS, "修改简介成功");
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/getPhoneCode")
    public String getPhoneCode(String phone) throws Exception {
        return sendSms(phone);
    }

    @ResponseBody
    @RequestMapping("/changePsw")
    public void changePsw(String phone, String password) {
        User user = userMapper.getUserByPhone(phone);
        user.setPassword(password);
        userMapper.updateUser(user);
    }


    private String getCode() {
        return userMapper.getUserById(128).getCode();
    }
}
