package com.avinash.kumar.notification_service.consumer;

import com.avinash.kumar.user_service.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {
//    @KafkaListener(topics="user-random-topic")
//    public void handleUserRandomTopic(String message){
//        log.info("message received : {}",message);
//    }

        @KafkaListener(topics="user-created-topic")
    public void handleUserCreatedTopic(UserCreatedEvent event){
        log.info("handle user created : {}",event);
    }
}
