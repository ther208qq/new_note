package com.javaweb.springboot_web.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.springboot_web.mapper.NoteMapper;
import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.service.NoteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class NoteConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private NoteService noteService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    //接收到消息后暂时输出
    @RabbitListener(queues="fanout.queue1")
    public void receiveMessage(Note note)  {

        try{
            //收到note实体，consumer处理note
            noteService.saveNote(note);

            //将对象转换成json
            String jsonNote = objectMapper.writeValueAsString(note);
            String key = "note:" + note.getId();

            redisTemplate.opsForValue().set(key, jsonNote, Duration.ofMinutes(10));
            System.out.println("消费成功 - 数据库已入库，Redis 缓存已更新，ID: " + note.getId());

        }catch(JsonProcessingException e){
            System.out.println("json转换失败："+e);
        }catch (Exception e){
            System.out.println("执行出现错误："+e);
            throw new RuntimeException("消费失败，触发重试", e);
        }
    }

    //接收到消息后暂时输出
    @RabbitListener(queues="fanout.queue2")
    public void print(String message){
        System.out.println("收到："+message);
    }
}
