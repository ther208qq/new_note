package com.javaweb.springboot_web.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.springboot_web.client.AiClient;
import com.javaweb.springboot_web.mapper.NoteMapper;
import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.pojo.NoteBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service//表示交给容器管理 表示Service层地bean对象
public class NoteServiceImp extends ServiceImpl<NoteMapper, Note> implements NoteService{

    @Autowired //自动注入
    private NoteMapper noteMapper;

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AiClient aiClient;

    @Override
    public List<Note> list(){

        String redisKey = "note:all";

        String value = redisTemplate.opsForValue().get(redisKey);

        if(value != null){
            return JSON.parseArray(value, Note.class);
        }

        List<Note> noteList = noteMapper.selectList(null);

        redisTemplate.opsForValue().set("notebook:all", JSON.toJSONString(noteList),5, TimeUnit.MINUTES);

        return noteList;
    }

    @Override
    public void insert(Note note){

        int res = noteMapper.insert(note);

        if(res>0){
            redisTemplate.delete("note:all");
        }

    }

    @Transactional
    @Override
    public boolean deleteById(Integer id){

        //先删数据库
        int result = noteMapper.deleteById(id);

        if(result > 0){
            redisTemplate.delete("note:all");
            return true;
        }
        return false;
    }

    @Override
    public boolean updateById(Note note){

        int lines = noteMapper.updateById(note);

        if(lines>0){
            redisTemplate.delete("note:all");
            return false;
        }
        return true;
    }

    @Override
    public void clear(){
        noteMapper.clear();

        redisTemplate.delete("note:all");
    }

    @Override
    public String summary(String content){
        String id = aiClient.summary(content);

        return id;
    }

    @Override
    public String check(String id){
        String res = aiClient.check(id);
        return res;
    }

}
