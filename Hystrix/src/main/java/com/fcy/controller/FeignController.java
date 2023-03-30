package com.fcy.controller;

import com.fcy.feign.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/2/24 10:20
 */
@RestController
public class FeignController {
    @Autowired
    private TestService testService ;

    @GetMapping("/feign")
    public Object getPaymentById(){
        System.out.println("feign-------");
        return testService.fcy() ;
    }
}