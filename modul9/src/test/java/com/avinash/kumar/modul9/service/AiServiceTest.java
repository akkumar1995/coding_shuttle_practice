package com.avinash.kumar.modul9.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiServiceTest {
    @Autowired
    private AiService aiService;
//    @Test
//    void getJoke() {
//        var joke = aiService.getJoke("dogs");
//        System.out.println(joke);
//    }

//    @Test
//    void getEmbeddingTest(){
//        String text = "Hi I am Avinash";
//        var result = aiService.getEmbedding(text);
//        System.out.println(Arrays.toString(result));
//    }

//    @Test
//    void ingestDataToVectorStore() {
//        aiService.ingestDataToVectorStore();
//    }

    @Test
    void testSimilaritySearch(){
        var res = aiService.similaritySearch("space movie");
        System.out.println(res);
    }
}