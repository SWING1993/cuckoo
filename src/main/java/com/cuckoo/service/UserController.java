package com.cuckoo.service;

import com.cuckoo.domain.User;
import com.cuckoo.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/user")
public class UserController {

    static Map<Long, User> users= Collections.synchronizedMap(new HashMap<Long, User>());

    @Autowired
    private UserMapper userMapper;

    // 注册用户
    @RequestMapping(value= "/register", method=RequestMethod.POST)
    public Object addUser(@ModelAttribute User user) {
        Map<String, String > resultMap = new HashMap<String, String>();
        if (user.getName().isEmpty() || user.getPassword().isEmpty() || user.getPhone().isEmpty()) {
            resultMap.put("code","1000");
            resultMap.put("message","用户名,密码.手机号不能为空");
            return resultMap;
        }
        resultMap.put("code","1000");
        resultMap.put("message","添加成功");
        userMapper.addUser(user);
        return resultMap;
    }


    // 注销用户
    @RequestMapping(value= "/delete", method=RequestMethod.POST)
    public Object deleteUserById(@RequestParam HashMap requestMap) {
        System.out.println("注销用户" +requestMap);
        Integer id = Integer.parseInt(requestMap.get("id").toString());
        userMapper.deleteUserById(id);
        Map<String, String > resultMap = new HashMap<String, String>();
        resultMap.put("code","1000");
        resultMap.put("message","注销成功");
        return resultMap;
    }


    // 查询全部
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> r = userMapper.getAllUsers();
        System.out.println(r);
        return r;
    }


    //根据id查询用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Integer id) {
        System.out.println(id);
        User user = userMapper.getUserById(id);
        return user;
    }

}
