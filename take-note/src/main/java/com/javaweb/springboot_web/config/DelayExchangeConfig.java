package com.javaweb.springboot_web.config;

import org.springframework.amqp.core.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DelayExchangeConfig {

    @Bean
    public DirectExchange delayExchange() {
        return ExchangeBuilder
                .directExchange("delay.direct")
                .delayed()
                .durable(true)
                .build();
    }

    @Bean
    public Queue delayQueue(){
        return new Queue("delay.queue");
    }

    @Bean
    public Binding delayQueueBinding(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delay");
    }

}
