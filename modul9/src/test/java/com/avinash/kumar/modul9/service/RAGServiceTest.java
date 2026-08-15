package com.avinash.kumar.modul9.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RAGServiceTest {
    @Autowired
    private  RAGService ragService;

//    @BeforeEach
//     void ingestData(){
//        ragService.ingestPDFToVectorStore();
//    }
//
//    @Test
//    void askTestAi(){
//        var response =ragService.askAi("How to contact mentors?");
//        System.out.println(response);
//    }

    @Test
    void askTestAiWithAdvisor(){
        var response =ragService.askWithAdvisors("what is my favourite food","Avinash Kumar");
        System.out.println(response);
    }

}