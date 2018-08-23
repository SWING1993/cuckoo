package com.cuckoo.service;

import com.cuckoo.domain.User;
import com.cuckoo.domain.UserMapper;
import com.cuckoo.utils.RestResult;
import com.cuckoo.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private UserMapper userMapper;

    //
    public UserMapper getUserMapper() {
        return userMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 用户注册
    @PostMapping(value= "/register")
    public RestResult<User> addUser(@ModelAttribute User user) throws Exception {
        userMapper.addUser(user);
        return RestResultGenerator.genSuccessResult();
    }

    // 用户登录
    @PostMapping(value= "/login")
    public RestResult<User> loginByUsername(@RequestParam HashMap requestMap) throws Exception {
        System.out.println("用户名登录" +requestMap);
        String username = requestMap.get("username").toString();
        String password = requestMap.get("password").toString();

        User user = userMapper.getUserByUsername(username);

        if (user == null) {
            return RestResultGenerator.genErrorResult("用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            return RestResultGenerator.genErrorResult("用户密码不正确");
        }
        return RestResultGenerator.genSuccessResult(user);
    }

    // 注销用户
    @DeleteMapping(value= "/delete")
    public RestResult<User> deleteUserById(@RequestParam HashMap requestMap) throws Exception {
        Long id = Long.parseLong(requestMap.get("id").toString());
        userMapper.deleteUserById(id);
        return RestResultGenerator.genSuccessResult();
    }

    // 查询全部
    @GetMapping(value = "/getAll")
    public RestResult<List<User>> getUserList() throws Exception {
        List<User> r = userMapper.getAllUsers();
        List<User> allUsers = new ArrayList<User>();
        for (int i = 0; i < r.size(); i ++) {
            User user = r.get(i);
            user.setPassword(null);
            allUsers.add(user);
        }
        System.out.println(allUsers);
        return RestResultGenerator.genSuccessResult(allUsers);
    }

    //根据id查询用户
    @GetMapping(value = "/getById")
    public RestResult<User> getUserById(@RequestParam HashMap requestMap) throws Exception {
        Long id = Long.parseLong(requestMap.get("id").toString());
        User user = userMapper.getUserById(id);
        return RestResultGenerator.genSuccessResult(user);
    }

    // 修改头像
    @PostMapping(value= "/updateAvatar")
    public RestResult<User> updateAvatar(@RequestParam HashMap requestMap) throws Exception {
        Long id = Long.parseLong(requestMap.get("id").toString());
        String avatar = requestMap.get("avatar").toString();
        userMapper.updateAvatarById(id,avatar);
        return RestResultGenerator.genSuccessResult();
    }

}
