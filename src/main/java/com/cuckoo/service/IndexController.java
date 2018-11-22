package com.cuckoo.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    // 根目录映射 Get访问方式 直接返回一个字符串

    @RequestMapping(value = {"/welcome", "/"})
    public String defaultPage() {
        return "Web Service data successfuly consumed";

    }
}
