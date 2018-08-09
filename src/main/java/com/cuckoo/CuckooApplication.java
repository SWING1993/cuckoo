package com.cuckoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CuckooApplication {
    public static void main(String[] args) {
        SpringApplication.run(CuckooApplication.class, args);
    }

    // 根目录映射 Get访问方式 直接返回一个字符串
    @RequestMapping("/")
    Map<String, String> hello() {
        // 返回map会变成JSON key value方式
        Map<String,String> map=new HashMap<String,String>();
        map.put("content", "hello freewolf~");
        return map;
    }
}
