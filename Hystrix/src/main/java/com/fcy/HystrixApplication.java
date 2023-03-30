package com.fcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/2/24 10:16
 */
@SpringBootApplication
@EnableFeignClients  // 开启使用Fegin
public class HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class);
    }
}
