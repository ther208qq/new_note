package com.javaweb.springboot_web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteCallConfig {

    @Bean
    public RemoteCallConfig getRemoteCallConfig(){
        return new RemoteCallConfig();
    }

}
