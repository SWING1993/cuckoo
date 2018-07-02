package com.cuckoo.service;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = java.lang.Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) {
        Map<String, Object > map = new HashMap<String, Object>();
        map.put("exception",e);
        map.put("url",req.getRequestURI());
        System.out.println(map);
        return map;
    }

}
