package fcy.config.redis;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * @@缓存注解@@第二步@@配置缓存参数解析规则(如果不需要自定义过期时间，可忽略此步骤)
 *
 * @Author: guoxy
 * @Description:
 * @Date: 2022/4/19
 */
@Slf4j
public class MyRedisCacheManager extends RedisCacheManager {


    protected MyRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    // 秒
    private static final String SECONDS = "SECONDS";
    // 分钟
    private static final String MINUTES = "MINUTES";
    // 小时
    private static final String HOUR = "HOUR";
    // 天
    private static final String DAY = "DAY";

    /**
     *  缓存参数设置
     *
     *  例：@Cacheable(cacheNames="BROKEN_TS_BZDM:getTsBzdm#10#SECONDS",key="#kind",sync = true)
     *  BROKEN_TS_BZDM:getTsBzdm#10#second
     *  以"#"分隔字符串，第一位是缓存名称，第二位是时间值，第三位是时间单位
     */
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        String[] array = StrUtil.split(name, "#");
        String cacheNames = array[0];
        // 默认不设置 失效时间
        try {
            if (array.length > 1) {
                long ttl = Long.parseLong(array[1]);
                if (array.length > 2) {
                    String unit = array[2];
                    switch (unit) {
                        case SECONDS:
                            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
                            break;
                        case MINUTES:
                            cacheConfig = cacheConfig.entryTtl(Duration.ofMinutes(ttl));
                            break;
                        case HOUR:
                            cacheConfig = cacheConfig.entryTtl(Duration.ofHours(ttl));
                            break;
                        case DAY:
                            cacheConfig = cacheConfig.entryTtl(Duration.ofDays(ttl));
                            break;
                        default:
                            return super.createRedisCache(cacheNames, cacheConfig);
                    }
                }
                // 默认单位：小时
                cacheConfig = cacheConfig.entryTtl(Duration.ofHours(ttl));
            }
        }catch (Exception e){
            log.error("缓存参数设置存在错误：参数cacheNames：{}，原因：{}", name, e.getMessage());
        }
        return super.createRedisCache(cacheNames, cacheConfig);
    }
}
