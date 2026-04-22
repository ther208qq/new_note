package com.jlu.gateway.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;

@Configuration
@EnableConfigurationProperties
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KeyPair keyPair(com.jlu.common.config.JwtProperties properties) {

        String path = properties.getLocation().replace("classpath:", "");
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        new ClassPathResource(path),
                        properties.getPassword().toCharArray()
                );

        return keyStoreKeyFactory.getKeyPair(properties.getAlias(),
                properties.getPassword().toCharArray());
    }
}
