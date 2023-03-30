package com.fcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/13 17:15
 */
@SpringBootApplication
@EnableEurekaClient
public class SentinelApplicaition {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplicaition.class,args);
    }
}
