package com.cuckoo.service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/ssr")
public class SSRController {

    // 查询全部
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getUserList() {


        StringBuffer buffer = new StringBuffer();

        try {
            // 读取原始json文件并进行操作和输出
            BufferedReader br = new BufferedReader(new FileReader("/Users/songguohua/Desktop/config/ssrconfig.json"));// 读取原始json文件
            String s = null;
            while ((s = br.readLine()) != null) {
                 System.out.println(s);
                buffer.append(s);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

}


