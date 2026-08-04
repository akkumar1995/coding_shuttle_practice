package com.avinash.kumar.module1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
@ConditionalOnProperty(name = "notification.type",havingValue="email")
public class EmailNotificationService implements NotificationService{
    @Override
    public void send(String message) {
        System.out.println("Email sent :"+message);
    }
}
