package jmsocialproject.springgateway.filter;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.jsonwebtoken.*;
import jmsocialproject.springgateway.validator.RouteValidation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@Log
public class GatewayFilterImpl implements GatewayFilter {

    @Autowired
    private RouteValidation validator;
    @Value("${jwt.secret}")
    private String jwtSecret;



    @Override
    @HystrixCommand(fallbackMethod = "getFilterFallback")
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {

        String originalUri = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, "Unknown");

        String jwtToken = exchange.getRequest().getHeaders().getFirst("authorization");

        if (jwtToken != null) {

            String roleName = getRoleName(jwtToken);

            if (!validator.checkMapping(roleName, originalUri)) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(UNAUTHORIZED);
                return response.setComplete();
            }

        } else if (!validator.isOpenApi(originalUri)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(BAD_REQUEST);
            return response.setComplete();
        }

        return chain.filter(exchange);


    }

    public Mono getFilterFallback()
    {
        return Mono.just("Gateway service is down!");
    }

    Mono<Void> onErrorFilter(ServerWebExchange exchange) {
        var response = exchange.getResponse();
        response.setStatusCode(BAD_REQUEST);
        return response.setComplete();

    }

    private String getRoleName(String jwtToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();

            return claims.get("role", String.class);

        } catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("Invalid token");
        }
        return null;
    }
}
