package com.javaweb.springboot_web.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutConfig {
    //声明交换机
    @Bean
    public FanoutExchange FanoutExchange(){
        return new FanoutExchange("temp.Fanout");
    }

    //声明队列
    @Bean
    public Queue FanoutQueue1(){
        return new Queue("fanout.queue1");
    }

    //绑定队列和fanout交换机
    @Bean
    public Binding bindingQueue1(){
        return BindingBuilder.bind(FanoutQueue1()).to(FanoutExchange());
    }

    //声明队列
    @Bean
    public Queue FanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    //绑定队列和fanout交换机
    @Bean
    public Binding bindingQueue2(){
        return BindingBuilder.bind(FanoutQueue2()).to(FanoutExchange());
    }
}
