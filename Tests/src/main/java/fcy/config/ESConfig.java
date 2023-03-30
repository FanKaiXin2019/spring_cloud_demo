package fcy.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/15 14:43
 */
@Configuration
public class ESConfig {

    //该对象可以对我们的ES进行相关的操作
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        //此处的ip为本地ip,可修改为指定对象的ip
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200,"http")));
        return client;
    }
}