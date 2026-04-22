package com.javaweb.springboot_web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.springboot_web.mapper.NoteMapper;
import com.javaweb.springboot_web.pojo.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;


@Component
public class RedisStreamConsumer implements StreamListener<String, MapRecord<String,String,String>> {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    //从监听容器获得消息，处理消息
    public void onMessage(MapRecord<String,String,String> message){

        try {
            Map<String,String> body = message.getValue(); //消息内容

            String type = body.get("type");
            String payload = body.get("payload");

            System.out.println("监听到消息类型: " + type);
            if ("INSERT_NOTE".equals(type)) {
                // 如果需要，可以在这里把 json 再转回 Note 对象
                 Note note = objectMapper.readValue(payload, Note.class);

                saveToDbAndCache(note);

                System.out.println("成功获取笔记内容，准备存入数据库...");
            }
            //业务逻辑
        } catch (JsonProcessingException e) {
            System.err.println("消费者处理消息异常: " + e.getMessage());
        }
    }

    private void saveToDbAndCache(Note note) throws JsonProcessingException {
        // A. 存入数据库
        noteMapper.insert(note);

        // B. 存入 Redis 缓存 (设置10分钟有效期)
        String key = "note:" + note.getId();
        String json = objectMapper.writeValueAsString(note);
        redisTemplate.opsForValue().set(key, json, Duration.ofMinutes(10));

    }

}
