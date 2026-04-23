package com.jlu.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

//提供给拦截器或网关用的
@Data
@Component
@ConfigurationProperties(prefix="jlu.auth")
public class AuthProperties {

    private List<String> includePaths;
    private List<String> excludePaths;

}

