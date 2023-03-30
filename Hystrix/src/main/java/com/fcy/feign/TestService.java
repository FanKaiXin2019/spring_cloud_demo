package com.fcy.feign;

import com.fcy.hystrix.Hystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/2/24 10:18
 */
@FeignClient(name="Tests",fallback = Hystrix.class)
@Component
public interface TestService {
    @GetMapping("/test/fcy")
    Object fcy() ;
}
