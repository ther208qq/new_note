package com.javaweb.springboot_web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("auth-service")
public interface UserClient {
    @GetMapping("/user/getnickname")
    String queryNicknameByUsername(@RequestParam("username") String username);
}
