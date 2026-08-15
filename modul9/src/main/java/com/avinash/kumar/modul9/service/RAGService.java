package com.avinash.kumar.modul9.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.VectorStoreChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class RAGService {
    @Autowired
    private  VectorStore vectorStore;
    @Autowired
    private  ChatClient chatClient;
    @Autowired
    private  ChatMemory chatMemory;

    @Value("classpath:faq.pdf")
    private Resource pdfFile;
    public void ingestPDFToVectorStore(){
        PagePdfDocumentReader reader = new PagePdfDocumentReader(pdfFile);
        List<Document> pages = reader.get();

        TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder().withChunkSize(200).build();
        List<Document> chunks = tokenTextSplitter.apply(pages);
        vectorStore.add(chunks);
    }

    public String askAi(String prompt){
        String template = """
                You are an AI assistant called Cody
                
                Rules:
                - Use ONLY the information provided in the context
                - You MAY rephrase, summarize, and explain in natural language
                - Do NOT introduce new concepts or facts
                - If multiple context sections are relevant, combine them into a single explanation.
                - If the answer is not present, say "I don't know"
                
                Context:
                {context}
                
                Answer in a friendly, conversational tone.
                """;
        var docs = vectorStore.similaritySearch(SearchRequest.builder().query(prompt).similarityThreshold(0.2)
                .filterExpression("file_name=='faq.pdf'").topK(4).build());
        var context = docs.stream().map(Document::getText).collect(Collectors.joining("\n\n"));
        PromptTemplate promptTemplate = new PromptTemplate(template);
        String stuffedPrompt = promptTemplate.render(Map.of("context",context));
        return chatClient.prompt().system(stuffedPrompt).user(prompt).advisors().call().content();
    }

    public String askWithAdvisors(String prompt,String userId){
        return chatClient.prompt().system("""
                You are an AI assistant called Cody
                Greet users with your name (Cody) and the user name if you know their name
                Answer in a friendly, conversational tone.
                """).user(prompt).advisors(
                MessageChatMemoryAdvisor.builder(chatMemory).conversationId(userId).build(),
                        VectorStoreChatMemoryAdvisor.builder(vectorStore).conversationId(userId).defaultTopK(4).build()
        ).call().content();
    }
}
