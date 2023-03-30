package fcy.config.redis;

import java.lang.annotation.*;

/**
 * @author 10028
 * @@分布式锁注解@@第一步@@定义注解
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock {

    // key前缀
    String prefix();

    // key值
    String value();
}
