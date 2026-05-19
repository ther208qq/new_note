package com.javaweb.springboot_web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MqConfig {
    //自动配置类，消息发送、接收时自动转换
    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();

        jackson2JsonMessageConverter.setCreateMessageIds(true);
        return jackson2JsonMessageConverter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //设置并使用自定义的消息转换器
        rabbitTemplate.setMandatory(true);//如果消息无法路由到任何队列，才会触发returncallback
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback(){
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage){
                log.error("触发return back");
                System.out.println("触发return back");
                log.error("exchange:{}",returnedMessage.getExchange());
                log.error("routingKey:{}",returnedMessage.getRoutingKey());
                log.error("replyCode:{}",returnedMessage.getReplyCode());
                log.error("replyText:{}",returnedMessage.getReplyText());
                log.error("message:{}",returnedMessage.getMessage());
            }
        });
        return rabbitTemplate;
    }

}
