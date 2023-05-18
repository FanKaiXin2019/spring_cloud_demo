package fcy.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/4/21 14:04
 */
@Component
public class RunAfter implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, String> getenv = System.getenv();
        Set<Map.Entry<String, String>> entries = getenv.entrySet();
        Iterator<Map.Entry<String, String>> iterator =  entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + "----" + next.getValue());
        }
        String deviceCode = System.getenv("deviceCode");
        System.err.println(deviceCode);
    }
}
