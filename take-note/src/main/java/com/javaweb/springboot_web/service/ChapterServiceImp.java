package com.javaweb.springboot_web.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.springboot_web.mapper.ChapterMapper;
import com.javaweb.springboot_web.mapper.NoteBookMapper;
import com.javaweb.springboot_web.pojo.Chapter;
import com.javaweb.springboot_web.pojo.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ChapterServiceImp extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService{

    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //查
    @Override
    public List<Chapter> list(){

        String redisKey = "chapters:all";
        String value = redisTemplate.opsForValue().get(redisKey);
        if(value != null){
            return JSON.parseArray(value, Chapter.class);
        }
        List<Chapter> chapterList = chapterMapper.selectList(null);

        redisTemplate.opsForValue().set(redisKey,JSON.toJSONString(chapterList),5, TimeUnit.MINUTES);
        return chapterList;
    }

    //增
    @Override
    public Chapter insert(Chapter chapter){
        chapterMapper.insert(chapter);
        redisTemplate.delete("chapters:all");
        return chapter;
    }
    //删
    @Override
    public boolean deleteById(Long id){

        int result = chapterMapper.deleteById(id);
        if(result>0){
            redisTemplate.delete("chapters:all");

            String value = redisTemplate.opsForValue().get("chapters:all");
            return true;
        }
        return false;
    }
    //更新
    @Override
    public boolean updateById(Chapter chapter){
        int result = chapterMapper.updateById(chapter);

        if(result>0){
            redisTemplate.delete("chapters:all");
            return true;
        }
        return false;
    }

    //清空
    @Override
    public void clear(){
        chapterMapper.delete(null);
        redisTemplate.delete("chapters:all");
    }
}
