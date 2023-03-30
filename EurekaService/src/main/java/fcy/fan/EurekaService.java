package fcy.fan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2021/12/14 11:21
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaService {
    public static void main(String[] args) {
        SpringApplication.run(EurekaService.class,args);
    }
}
