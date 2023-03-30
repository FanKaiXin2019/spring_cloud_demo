package fcy.fcy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2021/12/14 14:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplocation {

    @Value("${test.uri}")
    private String uri;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplocation.class,args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //basic proxy
                .route(r -> r.path("/**/**")
                        .uri(uri)
                ).build();
    }
}
