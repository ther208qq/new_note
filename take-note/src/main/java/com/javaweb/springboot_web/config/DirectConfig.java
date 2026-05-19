package com.javaweb.springboot_web.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    public DirectExchange directExchange(){return new DirectExchange("delete.direct");}

    @Bean
    public Queue DirectQueue(){return  new Queue("direct.queue");}

    @Bean
    public Binding bindingQueue(){return BindingBuilder.bind(DirectQueue()).to(directExchange()).with("d");}

    @Bean
    public DirectExchange directExchange1(){return new DirectExchange("delete1.direct");}

    @Bean
    public Queue DirectQueue1(){return  new Queue("direct1.queue");}

    @Bean
    public Binding bindingQueue1(){return BindingBuilder.bind(DirectQueue1()).to(directExchange1()).with("d1");}

    @Bean
    public DirectExchange directExchange2(){return new DirectExchange("delete2.direct");}

    @Bean
    public Queue DirectQueue2(){return  new Queue("direct2.queue");}

    @Bean
    public Binding bindingQueue2(){return BindingBuilder.bind(DirectQueue2()).to(directExchange2()).with("d2");}
}
