package com.avinash.kumar.module1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    PaymentService paymentService(){
        return new PaymentService();
    }
}
