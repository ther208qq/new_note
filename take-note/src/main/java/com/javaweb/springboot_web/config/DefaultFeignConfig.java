package com.javaweb.springboot_web.config;

import com.jlu.common.util.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultFeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template){

               Long userId = UserContext.getUserInfo();
               if(userId == null){
                   return;
               }
               template.header("user-info",String.valueOf(userId));
            }
        };
    }
}

