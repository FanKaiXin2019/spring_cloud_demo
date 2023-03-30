package fcy.controller;

import fcy.config.redis.RBloomFilterUtil;
import fcy.config.redis.RedisCommon;
import fcy.pojo.User;
import fcy.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/30 9:23
 */
@RestController
public class RedisController {
    @Autowired
    private RedisCommon redisCommon;

    private static final String MY_FRIENDS = "MY_FRIENDS:";

    @Autowired
    private RedisService testService;

    private static final HashMap<String, String> hashMap;

    static {
        hashMap = new HashMap<>();
        hashMap.put("dongdong","东东");
        hashMap.put("xiangxiang","翔翔");
        hashMap.put("dandan","蛋蛋");
        hashMap.put("yaya","牙牙");
    }

    @GetMapping("delFriend")
    public void delFriend(){
        redisCommon.delete(MY_FRIENDS + "xx");
        redisCommon.delete(MY_FRIENDS + "xx");
        redisCommon.delete(MY_FRIENDS + "dd");
        redisCommon.delete(MY_FRIENDS + "yy");
        redisCommon.delete(MY_FRIENDS + "dd2");
        redisCommon.delete(MY_FRIENDS + "friend");
    }

    @GetMapping("addFriend")
    public void addFriend(){
        delFriend();
        redisCommon.set(MY_FRIENDS + "xx","翔翔");
        redisCommon.set(MY_FRIENDS + "dd","东东");
        redisCommon.set(MY_FRIENDS + "yy","牙牙");
        redisCommon.set(MY_FRIENDS + "dd2","蛋蛋");


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("翔翔");
        arrayList.add("东东");
        arrayList.add("牙牙");
        arrayList.add("蛋蛋");
        redisCommon.set(MY_FRIENDS + "friend",arrayList);
    }

    @GetMapping("getFriend")
    public void getFriend(){
        System.out.println(redisCommon.get(MY_FRIENDS + "xx"));
        System.out.println(redisCommon.get(MY_FRIENDS + "dd"));
        System.out.println(redisCommon.get(MY_FRIENDS + "yy"));
        System.out.println(redisCommon.get(MY_FRIENDS + "dd2"));
        System.out.println(redisCommon.get(MY_FRIENDS + "friend"));
    }



    /**
     * 缓存注解的使用
     */
    @GetMapping("cacheable")
    private void cacheable(){
        hashMap.forEach((key,val)->{
            testService.addFriends(key,val);
        });
    }


    @Autowired
    private RBloomFilterUtil rBloomFilterUtil;
    /**
     * @@布隆过滤器@@第二步@@测试key是否存在
     */
    @GetMapping("/rBoom")
    private void rBoom(){
        System.out.println("拦截key是否存在："+rBloomFilterUtil.isContains("java"));
        System.out.println("拦截key是否存在："+rBloomFilterUtil.isContains("123"));
        System.out.println("拦截key是否存在："+rBloomFilterUtil.isContains("abc"));
    }


    @GetMapping("/addFriendInfo/{idCard}")
    private void addFriendInfo(@PathVariable String idCard){
        testService.addFriendInfo(new User().setUserId(UUID.randomUUID().toString())
                .setNickname("nickname")
                .setName("name")
                .setIdCard(idCard));
    }
}
