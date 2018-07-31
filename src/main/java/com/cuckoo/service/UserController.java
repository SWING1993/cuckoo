package com.cuckoo.service;

import com.cuckoo.domain.User;
import com.cuckoo.domain.UserMapper;
import com.cuckoo.utils.MD5Str;
import com.cuckoo.utils.ResultModel;
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
        ResultModel resultModel = new ResultModel();
        resultModel.setUrl("/user/register");

        if (user.getName().isEmpty() || user.getPassword().isEmpty() || user.getPhone().isEmpty()) {
            resultModel.setMessage("用户名,密码,手机号不能为空");
            resultModel.setCode(1001);
            return resultModel;
        }

        if (user.getPhone().length() != 11) {
            resultModel.setMessage("请填写正确的手机号");
            resultModel.setCode(1001);
        } else if (user.getPassword().length() < 6) {
            resultModel.setMessage("密码最小六位");
            resultModel.setCode(1001);
        } else {
            String md5Password = MD5Str.getMD5(user.getPassword());
            user.setPassword(md5Password);
            userMapper.addUser(user);
            resultModel.setCode(1000);
            resultModel.setMessage("注册成功");
        }
        return resultModel;
    }

    // 注销用户
    @RequestMapping(value= "/delete", method=RequestMethod.POST)
    public Object deleteUserById(@RequestParam HashMap requestMap) {
        System.out.println("注销用户" +requestMap);
        ResultModel resultModel = new ResultModel();
        resultModel.setUrl("/user/delete");
        Integer id = Integer.parseInt(requestMap.get("id").toString());
        if (id == 0) {
            resultModel.setCode(1001);
            resultModel.setMessage("id不能为空");
            return resultModel;
        }
        userMapper.deleteUserById(id);
        resultModel.setCode(1000);
        resultModel.setMessage("处理成功");
        return resultModel;
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
