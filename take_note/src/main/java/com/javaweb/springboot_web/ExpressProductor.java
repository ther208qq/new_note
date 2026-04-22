package com.javaweb.springboot_web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.springboot_web.pojo.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


//生产者 负责生产并发送消息

@Service
public class ExpressProductor {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    //传送内容 从controller获取note对象
    public void sendNote(Note note) throws JsonProcessingException {

        //将对象转换成json
        String jsonNote = objectMapper.writeValueAsString(note);

        Map<String,String> content = new HashMap<>();
        content.put("payload",jsonNote);
        content.put("type","INSERT_NOTE");

        //redis stream用来存储消息，给监听容器监听 ObjectRecord用来封装并发送消息 消费者不断从stream中拉取消息
        //总的来说就是把content封装成一个消息对象
        //创建一个消息对象StreamRecords.newRecord() 指定发送到db-stream这个队列 in("db-stream") 放入content ofObject(content)
        MapRecord<String,String,String> record = StreamRecords.newRecord().in("db-stream").ofMap(content);

        //存入redis中
        this.redisTemplate.opsForStream().add(record);

    }

}
