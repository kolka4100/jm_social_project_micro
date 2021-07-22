package jmsocialproject.springgateway.config;

import jmsocialproject.springgateway.filter.GatewayFilterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class ProxyConfig {

    @Autowired
    GatewayFilter gatewayFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("login-service_route",
                        route -> route.path("/api/rest/auth/**")
                                .and()
                                .method(HttpMethod.POST)
                                .filters(f -> f
                                        .filter(gatewayFilter))
                                .uri("http://localhost:8000/api/rest/auth/"))
                .build();
    }
}
