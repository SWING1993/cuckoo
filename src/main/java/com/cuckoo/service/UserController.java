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
    static Map<Long, User> users= Collections.synchronizedMap(new HashMap<Long, User>());

    @Autowired
    private UserMapper userMapper;

    // 注册用户
    @RequestMapping(value= "/register", method=RequestMethod.POST)
    public RestResult<User> addUser(@ModelAttribute User user) throws Exception {

        String hashAlgorithmName = "MD5";
        String credentials = user.getPassword();
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("password");
        Object passwordMd5 = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);

        user.setPassword(passwordMd5.toString());
        userMapper.addUser(user);
        return RestResultGenerator.genSuccessResult();


//        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getPhone().isEmpty()) {
//            resultModel.setMessage("用户名,密码,手机号不能为空");
//            resultModel.setCode(1001);
//            return resultModel;
//        }
//
//        if (user.getPhone().length() <= 0) {
//            resultModel.setMessage("请填写正确的手机号");
//            resultModel.setCode(1001);
//        } else if (user.getPassword().length() < 6) {
//            resultModel.setMessage("密码最小六位");
//            resultModel.setCode(1001);
//        } else {
//            String hashAlgorithmName = "MD5";
//            String credentials = user.getPassword();
//            int hashIterations = 1024;
//            ByteSource credentialsSalt = ByteSource.Util.bytes("password");
//            Object passwordMd5 = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
//
//            user.setPassword(passwordMd5.toString());
//            userMapper.addUser(user);
//            resultModel.setCode(1000);
//            resultModel.setMessage("注册成功");
//        }
//        return resultModel;
    }

    // 注销用户
    @RequestMapping(value= "/delete", method=RequestMethod.POST)
    public RestResult<User> deleteUserById(@RequestParam HashMap requestMap) throws Exception {
        System.out.println("注销用户" +requestMap);
        Integer id = Integer.parseInt(requestMap.get("id").toString());
        userMapper.deleteUserById(id);
        return RestResultGenerator.genSuccessResult();
    }

    // 查询全部
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public RestResult<List<User>> getUserList() throws Exception {
        List<User> r = userMapper.getAllUsers();
        System.out.println(r);
        return RestResultGenerator.genSuccessResult(r);
    }

    //根据id查询用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestResult<User> getUserById(@PathVariable Integer id) throws Exception {
        System.out.println(id);
        User user = userMapper.getUserById(id);
        return RestResultGenerator.genSuccessResult(user);
    }

}
