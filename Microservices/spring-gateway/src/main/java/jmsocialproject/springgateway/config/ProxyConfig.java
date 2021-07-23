package jmsocialproject.springgateway.config;

import jmsocialproject.springgateway.filter.GatewayFilterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class ProxyConfig {

    @Autowired
    private GatewayFilter gatewayFilter;

    @Value("${login.service.name}")
    private String loginServiceName;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(loginServiceName,
                        route -> route.path("/api/rest/auth/**")
                                .filters(f -> f
                                        .filter(gatewayFilter))
                                .uri("lb://" + loginServiceName))
                .build();
    }
}
