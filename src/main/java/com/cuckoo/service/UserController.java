package com.cuckoo.service;

import com.cuckoo.domain.User;
import com.cuckoo.domain.UserMapper;
import com.cuckoo.utils.RestResult;
import com.cuckoo.utils.RestResultGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 用户注册
    @RequestMapping(value= "/register", method=RequestMethod.POST)
    public RestResult<User> addUser(@ModelAttribute User user) throws Exception {

        // 加密密码
        String hashAlgorithmName = "MD5";
        String credentials = user.getPassword();
        int hashIterations = 1024;
        // 用户名作为盐
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        Object passwordMd5 = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        user.setPassword(passwordMd5.toString());

        userMapper.addUser(user);
        return RestResultGenerator.genSuccessResult();
    }

    // 用户登录
    @RequestMapping(value= "/login", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public RestResult<User> loginByUsername(@RequestParam HashMap requestMap) throws Exception {
        System.out.println("用户名登录" +requestMap);
        String username = requestMap.get("username").toString();
        String password = requestMap.get("password").toString();

        User user = userMapper.getUserByUsername(username);

        if (user == null) {
            return RestResultGenerator.genErrorResult("用户不存在");
        }

        // 加密密码
        String hashAlgorithmName = "MD5";
        String credentials = password;
        int hashIterations = 1024;
        // 用户名作为盐
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        Object passwordMd5 = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        if (!user.getPassword().equals(passwordMd5.toString())) {
            return RestResultGenerator.genErrorResult("用户密码不正确");
        }
        return RestResultGenerator.genSuccessResult(user);
    }

    // 注销用户
    @RequestMapping(value= "/delete", method=RequestMethod.POST)
    public RestResult<User> deleteUserById(@RequestParam HashMap requestMap) throws Exception {
        Long id = Long.parseLong(requestMap.get("id").toString());
        userMapper.deleteUserById(id);
        return RestResultGenerator.genSuccessResult();
    }

    // 查询全部
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestResult<User> getUserById(@PathVariable Long id) throws Exception {
        User user = userMapper.getUserById(id);
        return RestResultGenerator.genSuccessResult(user);
    }

    // 修改头像
    @RequestMapping(value= "/upadteAvatar", method=RequestMethod.POST)
    public RestResult<User> upadteAvatar(@RequestParam HashMap requestMap) throws Exception {
        Long id = Long.parseLong(requestMap.get("id").toString());
        String avatar = requestMap.get("avatar").toString();
        userMapper.updateAvatarById(id,avatar);
        return RestResultGenerator.genSuccessResult();
    }

}
