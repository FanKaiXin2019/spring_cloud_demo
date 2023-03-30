package com.fcy.hystrix;

import com.fcy.feign.TestService;
import org.springframework.stereotype.Component;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/2/24 10:58
 */
@Component
public class Hystrix implements TestService {
    @Override
    public Object fcy() {
        return "触发熔断";
    }
}
