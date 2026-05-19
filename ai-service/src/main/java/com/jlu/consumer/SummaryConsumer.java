package com.jlu.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SummaryConsumer {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.deepseek.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Authorization","Bearer sk-9fbe0d614f9842e1bf0e2e831b73222c")
            .build();

    @RabbitListener(queues = "summary.queue")
    public void summary(String jsonMessage){
        Map<String,Object> request = new HashMap<>();
        try{
            //**
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonMessage);
            String id = root.get("id").asText();
            String content = root.get("content").asText();

            request.put("model","deepseek-chat");
            request.put("messages", List.of(
                    Map.of("role","system","content","你是一个笔记总结助手"),
                    Map.of("role","user","content","请总结以下内容:"+content)
            ));

            String summary = webClient.post()
                    .uri("/v1/chat/completions")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(node -> node.path("choices").get(0).path("message").path("content").asText())
                    .block();

            String redisKey = "summary:"+id;

            redisTemplate.opsForValue().set(redisKey,summary,5,TimeUnit.MINUTES);

        }catch (Exception e){
            System.err.println("解析失败"+e.getMessage());
        }
    }

}
