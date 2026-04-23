package com.jlu.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

//创建并初始化Bean容器
@Configuration
@ConditionalOnProperty(name = "jlu.jwt.sharding", havingValue = "true")
public class JwtConfig {

    @Bean
    @ConditionalOnMissingBean
    public KeyPair keyPair(JwtProperties prop)throws Exception
    {
        String location = prop.getLocation();
        if (location != null && location.startsWith("classpath:")) {
            location = location.replace("classpath:", "");
        }

        KeyStore keyStore = KeyStore.getInstance("JKS");

        ClassPathResource resource = new ClassPathResource(location);

        try (InputStream is = resource.getInputStream()) {
            keyStore.load(is, prop.getPassword().toCharArray());
        }

        PrivateKey privateKey = (PrivateKey) keyStore.getKey(prop.getAlias(), prop.getPassword().toCharArray());
        PublicKey publicKey = keyStore.getCertificate(prop.getAlias()).getPublicKey();

        return new KeyPair(publicKey, privateKey);
    }

}
