package com.avinash.kumar.api_gateway.filters;

import com.avinash.kumar.api_gateway.service.JwtService;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final JwtService jwtService;
    public AuthenticationFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService =  jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//            if(authHeader==null) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//            String token = authHeader.split("Bearer ")[1];
//            Long userId = jwtService.getUSerIdFomToken(token);
//            exchange.getRequest().mutate().header("X-User-Id",userId.toString()).build();
            return chain.filter(exchange);
        };
    }


    public static class Config {

    }
}
