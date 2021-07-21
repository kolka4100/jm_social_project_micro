package jmsocialproject.springgateway.filter;

import io.jsonwebtoken.*;
import jmsocialproject.springgateway.validator.RouteValidation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

@Component
public class GatewayFilterImpl implements GatewayFilter {

    @Autowired
    RouteValidation validator;




    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {

        var originalUri = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, "Unknown");

        if (validator.isOpenApi(originalUri)) {
            //TODO return All OK

            return null;
        } else {

            var headers = exchange.getRequest().getHeaders();

            // TODO изменить на авторизейшен как хочет лид (проверить наличие хедера авторизейшен)
            var jvtToken = headers.getFirst("JvtToken");

            if (jvtToken != null && validator.validateToken(jvtToken)) {
                //TODO return All OK
                return null;
            }

            //TODO return Bad Token
            return null;
        }
    }


}
