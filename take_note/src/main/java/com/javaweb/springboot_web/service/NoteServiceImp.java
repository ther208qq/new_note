package com.javaweb.springboot_web.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.springboot_web.ExpressProductor;
import com.javaweb.springboot_web.RedisStreamConsumer;
import com.javaweb.springboot_web.mapper.NoteMapper;
import com.javaweb.springboot_web.pojo.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;


@Slf4j
@Service//表示交给容器管理 表示Service层地bean对象
public class NoteServiceImp extends ServiceImpl<NoteMapper, Note> implements NoteService{

    @Autowired //自动注入
//    private NoteDao noteDao;
    private NoteMapper noteMapper;

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ExpressProductor productor;

    //将对象转换成jackson字符串
    private final ObjectMapper objectMapper = new ObjectMapper();// jackson工具


    @Override
    public List<Note> findAll(){


        List<Note> noteList = noteMapper.findAll();

        return noteList;
    }

    @Override
    public void insert(Note note){


//        log.info("数据库写入成功，ID: {}", note.getId());

        try {
            // 2. 发送给消息队列，不直接操作数据库
            productor.sendNote(note);
            log.info("笔记已发送至消息队列，ID: {}", note.getId());
        } catch (Exception e) {
//            log.error("发送消息失败", e);

            log.error("Redis 异步操作失败（可能是 Redis 挂了），但不影响数据库存盘。错误原因: {}", e.getMessage());
            //这样即使redis挂了也能正常传入数据库，同时也不影响传入速度
            noteMapper.insert(note);
//            throw new RuntimeException("系统繁忙，请稍后再试");
        }
    }

    @Transactional
    @Override
    public Integer deleteById(Integer id){

        int result = noteMapper.deleteById(id);

        if(result > 0){
            String key = "note:" + id;

            //异步
            CompletableFuture.runAsync(() -> {
                try {
                    redisTemplate.delete(key);
                } catch (Exception e) {
                    log.warn("Redis失败", e);
                }
            });

        }

        return result;
    }

    @Override
    public boolean updateById(Note note){

        int lines = noteMapper.updateById(note);

        if(lines>0){
            String key = "note:" + note.getId();

            try{
                redisTemplate.delete(key);//删除旧缓存
                log.info("数据库更新成功，已清理 Redis 缓存 Key: {}", key);

            }catch (Exception e){
                log.error("更新后清理 Redis 缓存失败，ID: {}", note.getId(), e);
            }
        }
        return true;
    }

    @Override
    public void clear(){
        noteMapper.clear();

        try {
            Set<String> keys = redisTemplate.keys("note:*");
            if (keys != null && !keys.isEmpty()) {
                redisTemplate.delete(keys);
                log.info("已清空所有笔记缓存，共 {} 条", keys.size());
            }
        } catch (Exception e) {
            log.error("清空 Redis 缓存失败", e);
        }

    }

}
