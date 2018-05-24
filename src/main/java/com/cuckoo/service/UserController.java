package com.cuckoo.service;


import com.cuckoo.domain.User;
import com.cuckoo.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    static Map<Long, User> users= Collections.synchronizedMap(new HashMap<Long, User>());

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
//        List<User> r = new ArrayList<>(users.values());
        List<User> r = userMapper.getAllUsers();
        return r;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(int id) {
        User user = userMapper.getUserById(id);
        return user;
    }




}
