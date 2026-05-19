package com.jlu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    @Bean
    public DirectExchange DirectExchange(){return new DirectExchange("summary.direct");}

    @Bean
    public Queue SummaryQueue(){return new Queue("summary.queue");}

    @Bean
    public Binding bindingQueueToExchange(){return BindingBuilder.bind(SummaryQueue()).to(DirectExchange()).with("summary");
    }
}
