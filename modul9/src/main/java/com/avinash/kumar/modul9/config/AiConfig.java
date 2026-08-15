package com.avinash.kumar.modul9.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }

    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository repository){
        return MessageWindowChatMemory.builder().
        chatMemoryRepository(repository).maxMessages(10).build();
    }
}
