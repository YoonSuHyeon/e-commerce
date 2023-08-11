package com.ysh.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator configurationRoute(RouteLocatorBuilder rlb) {
        return rlb.routes()
                .route("userId", r -> r.path("/api/users/**").uri("lb://user-service"))
                .route("userId", r -> r.path("/api/users/**").uri("lb://user-service"))
                .build();
    }

}
