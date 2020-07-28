package com.spider.school.controller;

import com.spider.school.Util.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonFileController {

    @RequestMapping("/test")
    public Object test(){
        return ResponseData.OK("OK");
    }

}
