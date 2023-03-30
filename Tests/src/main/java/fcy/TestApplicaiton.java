package fcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/2/24 10:16
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class TestApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(TestApplicaiton.class);
    }
}
