package com.jlu.common.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Data
@Component
@ConfigurationProperties(prefix="jlu.jwt")
public class JwtProperties {


    private String location;
    private String alias;
    private String password;
    private Duration tokenTTL;

}
