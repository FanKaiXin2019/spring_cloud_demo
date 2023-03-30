package fcy.controller;

import fcy.config.CheckRole;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/2/24 10:20
 */
@RestController()
@RequestMapping("/test")
public class TestController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/fcy")
    public Object fcy() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        redisTemplate.opsForValue().set("idea", "test");
        log.error("看到我 你就成功了");
        return list;
    }

    @GetMapping("/fcy2")
    public Object fcy2() {
        for (int i = 0; i < 100000; i++) {
            redisTemplate.opsForValue().setBit("bm", i, true);
        }
        int[] arr = new int[10000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < 2000; i++) {
            arr[i + 8000] = 100000 + i;
        }
        float error = 0;
        for (int i : arr) {
            Boolean bm = redisTemplate.opsForValue().getBit("bm", i);
            if (bm == false) {
                error++;
            }
        }
        return "正确率:" + error / 10000;
    }

    @CheckRole
    @GetMapping("/fkx")
    public Object fkx() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        log.error("看到我 你就成功了");
        return list;
    }


    @GetMapping("/es")
    public Object es() throws IOException {
        // SearchRequest
        SearchRequest tbuser = new SearchRequest("tbuser");

        // 构建检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页采用简单的from + size分页，适用数据量小的，了解更多分页方式可自行查阅资料
        //        searchSourceBuilder.from((page - 1) * rows);
        //        searchSourceBuilder.size(rows);
        // 查询所有
        //        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        // 根据字段匹配
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("id", "1");
        searchSourceBuilder.query(queryBuilder);

        tbuser.source(searchSourceBuilder);
        // 查询ES
        SearchResponse searchResponse = restHighLevelClient.search(tbuser, RequestOptions.DEFAULT);
        System.out.println("查询结果：" + searchResponse.toString());
        SearchHits hits = searchResponse.getHits();
        // 遍历封装列表对象
//        List<User> userList = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getSourceAsString());
        }
        return null;
    }


    @GetMapping("/error")
    public Object error() {
        float f = 1 / 0;
        return f;
    }
}