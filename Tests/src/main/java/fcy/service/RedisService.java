package fcy.service;

import fcy.config.redis.DistributeLock;
import fcy.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * com.oak.springbootredis -> TestService
 *
 * @Author: guoxy
 * @Description:
 * @Date: 2022/4/26
 */
@Service
public class RedisService {

    /**
     * @@缓存注解@@第三步@@使用注解存储结果集到redis缓存
     * 此处@Cacheable（获取返回值，没有则执行方法，并添加到缓存）
     * 另外还有@CacheEvict（清除缓存）、@CachePut（新增/更新缓存）注解可用
     */
    @Cacheable(cacheNames="MY_FRIEND:addFriends",key = "#nickname")
    public User addFriends(String nickname,String name){
        return new User().setUserId(UUID.randomUUID().toString())
                .setNickname(nickname)
                .setName(name);
    }
    @CacheEvict(cacheNames="MY_FRIEND:addFriends",key = "#nickname")
    public void delFriends(){
        return;
    }

    /**
     * @@分布式锁注解@@第三步@@如果存在多个idCard一样的线程同时执行此方法，将会排队执行
     */
    @DistributeLock(prefix = "USER_INFO:",value = "#user.idCard")
    public void addFriendInfo(User user){
        user.setName("123");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}