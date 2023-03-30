package com.fcy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/13 17:17
 */
@RestController()
public class DemoController {
    @GetMapping("/demo")
    @SentinelResource("demo")
    public String demo(){
        return "1234";
    }
}
