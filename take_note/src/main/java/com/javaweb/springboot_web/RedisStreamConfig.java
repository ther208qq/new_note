package com.javaweb.springboot_web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;

//监听容器配置
@Configuration
public class RedisStreamConfig {

    @Bean
    public StreamMessageListenerContainer<String, MapRecord<String,String,String>>streamMessageListenerContainer(RedisConnectionFactory factory, RedisStreamConsumer consumer){

        //配置信息
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String,MapRecord<String,String,String>> options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder().batchSize(10).pollTimeout(Duration.ofSeconds(2)).build();

        //设置监听容器实例
        StreamMessageListenerContainer<String,MapRecord<String,String,String>> container = StreamMessageListenerContainer.create(factory,options);

        try {
            // 尝试创建消费组，如果已存在会抛异常，我们捕获即可
            factory.getConnection().streamCommands().xGroupCreate(
                    "db-stream".getBytes(), "db-group", ReadOffset.from("0"), true);
        } catch (Exception e) {
            // 组已存在，无须处理
        }

        //委派具体任务    db-group和db-stream需要在redis中先创建好
        //只要redis中存在db-group，那java就可以根据db-group去redis stream访问拿到消息，并且用consumer-1来访问
        //这个方法能把消息传给consumer实例，这个实例就进行根据类进行处理消息
        container.receive(Consumer.from("db-group","consumer-1"),
                StreamOffset.create("db-stream", ReadOffset.from("0")),
                consumer);
        container.start();

        return container;
    }

}
