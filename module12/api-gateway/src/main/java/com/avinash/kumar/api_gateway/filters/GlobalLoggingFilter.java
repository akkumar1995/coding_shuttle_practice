package com.avinash.kumar.api_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class GlobalLoggingFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Logging from global pre "+exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(()-> {
            System.out.println("logging from global post");
        }));
    }

    @Override
    public int getOrder() {
        return 5;
    }
}
