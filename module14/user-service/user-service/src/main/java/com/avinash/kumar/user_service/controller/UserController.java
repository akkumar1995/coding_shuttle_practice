package com.avinash.kumar.user_service.controller;

import com.avinash.kumar.user_service.dto.CreateUserRequestDto;
import com.avinash.kumar.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Value("${kafka.topic.user-random-topic}")
    private String randomTopic;

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final UserService userService;

    @PostMapping("/message/{msg}")
    public ResponseEntity<String> getMessage(@PathVariable String msg){
        kafkaTemplate.send(randomTopic,msg);
        return ResponseEntity.ok("message Queued");
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto);
        return ResponseEntity.ok("User is created");
    }
}
