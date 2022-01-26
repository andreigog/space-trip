package com.andreigog.apigateway;

import com.andreigog.apigateway.security.SecurityAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({SecurityAuthProperties.class})
public class ApiGatewayApplication {

//  @Bean
//  RouteLocator gateway(RouteLocatorBuilder rlb) {
//    return rlb.routes()
//        .route(routeSpec -> routeSpec
//            .path("/v1/planets")
//            .filters(f -> f.addRequestHeader("dsa", "sdas"))
//
//            .uri("lb://planet-service/")
//        )
//        .build();
//  }

  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayApplication.class, args);
  }

}
