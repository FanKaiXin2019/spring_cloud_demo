package fcy.config.redis;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @@缓存注解@@第二步@@配置类
 *
 * @Author: guoxy
 * @Description:
 * @Date: 2022/5/5
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // config.useClusterServers().addNodeAddress("redis://" + host + ":" + port); // 分片集群方式
        SingleServerConfig server = config.useSingleServer();
        config.setLockWatchdogTimeout(5 * 1000L);
        server.setAddress("redis://" + host + ":" + port);
        if (StrUtil.isNotBlank(password)) {
            server.setPassword(password);
        }
        return Redisson.create(config);
    }
}

