package com.javaweb.springboot_web.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.springboot_web.client.UserClient;
import com.javaweb.springboot_web.mapper.NoteBookMapper;

import com.javaweb.springboot_web.pojo.NoteBook;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


//Service 无需编写任何service代码
@Service
@RequiredArgsConstructor //什么构造器
public class NoteBookServiceImp extends ServiceImpl<NoteBookMapper,NoteBook> implements NoteBookService {


    private final UserClient userClient;

    private final NoteBookMapper noteBookMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String CACHE_KEY = "notebook:all";

    //测试
    @Override
    @GlobalTransactional
    public String getUserNickname(String username) {

//        //在service层通过用户信息进行业务操作
//        Long userId = UserContext.getUserInfo();
        //调用auth-service微服务
        String res = userClient.queryNicknameByUsername(username);

        return res != null ? res:"默认昵称";
    }

    @Override
    public List<NoteBook> list(){

        String value = redisTemplate.opsForValue().get(CACHE_KEY);

        if(value != null){
            return JSON.parseArray(value, NoteBook.class);
        }

        List<NoteBook> noteBookList = noteBookMapper.selectList(null);

        redisTemplate.opsForValue().set(CACHE_KEY, JSON.toJSONString(noteBookList),5, TimeUnit.MINUTES);

        return noteBookList;
    }

    @Override
    public NoteBook selectById(Long id){

        NoteBook noteBook = noteBookMapper.selectById(id);

        if(noteBook!=null){
            return noteBook;
        }

        return null;
    }

    @Override
    public boolean insert(NoteBook noteBook){

        boolean res= save(noteBook);
        if(res){
            redisTemplate.delete(CACHE_KEY);
        }

        return true;
    }

    @Override
    public boolean updateById(NoteBook noteBook){

        int res = noteBookMapper.updateById(noteBook);

        if(res>0){
            redisTemplate.delete(CACHE_KEY);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id){

        //先删除数据库，删除redis
        int res = noteBookMapper.deleteById(id);

        redisTemplate.delete(CACHE_KEY);

        return false;
    }

}
