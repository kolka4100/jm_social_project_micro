package jmsocialproject.springgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class ProxyConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("login-service_route",
                        route -> route.path("/api/rest/auth")
                                .and()
                                .method(HttpMethod.POST)
                                .uri("http://localhost:8000/api/rest/auth"))
                .build();
    }
}
