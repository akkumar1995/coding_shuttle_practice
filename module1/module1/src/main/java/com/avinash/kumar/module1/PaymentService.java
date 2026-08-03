package com.avinash.kumar.module1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    public void pay(){
        System.out.println("Pay");
    }
    @PostConstruct
    public void afterInit(){
        System.out.println("Before Pay");
    }
    @PreDestroy
    public void beforeDestroy(){
        System.out.println("After Pay");
    }
}
