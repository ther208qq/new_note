package com.javaweb.springboot_web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ai-service")
public interface AiClient {

    @PostMapping("/summary")
    String summary(@RequestBody String content);

    @GetMapping("/summary/check/{id}")
    String check(@PathVariable String id);
}
