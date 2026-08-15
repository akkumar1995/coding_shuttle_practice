package com.avinash.kumar.modul9.service;

import com.avinash.kumar.modul9.dto.JokeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@RequiredArgsConstructor
public class AiService {
    @Autowired
    private  ChatClient chatClient;
    @Autowired
    private  EmbeddingModel embeddingModel;
    @Autowired
    private  VectorStore vectorStore;

    public float[] getEmbedding(String text){
        return embeddingModel.embed(text);
    }

    public String getJoke(String topic){
         String systemPrompt = """
                 you are a sarcastic joke, give a joke in 1 line for topic :{topic}
                 """;
        PromptTemplate promptTemplate = new PromptTemplate(systemPrompt);
        String renderText = promptTemplate.render(Map.of("topic",topic));
        JokeDto joke =  chatClient.prompt().user(renderText)
                .advisors(new SimpleLoggerAdvisor())
                .call().entity(JokeDto.class);
        assert joke != null;
        return joke.text();
    }

    public void ingestDataToVectorStore() {
        List<Document> movies = List.of(
                new Document("A thief who steals corporate secrets through the use of dream-sharing technology.",
                        Map.of("title", "Inception", "genre", "Sci-Fi", "year", 2010)),

                new Document("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                        Map.of("title", "Interstellar", "genre", "Sci-Fi", "year", 2014)),

                new Document("A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom.",
                        Map.of("title", "The Notebook", "genre", "Romance", "year", 2004))
        );
        vectorStore.add(movies);
    }

    public List<Document> similaritySearch(String text) {
        return vectorStore.similaritySearch(SearchRequest.builder()
                .query(text)
                .topK(3)
                .similarityThreshold(0.8)
                .build());
    }

    public String aiSkill(String prompt){
        return chatClient.prompt().user(prompt).call().content();
    }
}
