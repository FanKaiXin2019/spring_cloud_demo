package fcy.config.redis;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 10028
 * @@布隆过滤器@@第一步@@定义过滤器及过滤值
 **/
@Component
public class RBloomFilterUtil {
    @Autowired
    private RedissonClient redissonClient;
    private RBloomFilter<String> bloomFilter;

    @PostConstruct
    private void initBloomFilter(){
        this.bloomFilter = redissonClient.getBloomFilter("language");
        //初始化布隆过滤器：预计元素为10000L,误差率为3%
        this.bloomFilter.tryInit(10000L,0.03);
        bloomFilter.add("java");
        bloomFilter.add("php");
        bloomFilter.add("c#");
    }

    /**
     * 判断元素是否合法
     * @param id
     * @return
     */
    public boolean isContains(String id){
        return this.bloomFilter.contains(id);
    }

    public void put(String id){
        this.bloomFilter.add(id);
    }


}
