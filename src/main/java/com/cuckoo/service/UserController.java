package com.cuckoo.service;


import com.cuckoo.domain.User;
import com.cuckoo.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    static Map<Long, User> users= Collections.synchronizedMap(new HashMap<Long, User>());

    @Autowired
    private UserMapper userMapper;


    // 增
    @RequestMapping(value= "/add", method=RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        System.out.println(user);
        userMapper.addUser(user);
        return "success";
    }


    // 删
    @RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
    public String deleteUserById(@PathVariable Integer id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        userMapper.deleteUserById(id);
        return "success";
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
