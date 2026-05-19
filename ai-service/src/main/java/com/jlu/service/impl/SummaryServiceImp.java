package com.jlu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlu.entity.Message;
import com.jlu.service.SummaryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SummaryServiceImp implements SummaryService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String summary(String content){

        Message message = new Message();

        try{

            String lastKey = "summary:last";

            String last = redisTemplate.opsForValue().get(lastKey);

            if(last != null && last.equals(content)){

                return "请勿重复总结";
            }

            message.setId(UUID.randomUUID().toString());

            message.setContent(content);

            ObjectMapper mapper = new ObjectMapper();
            String jsonMessage = mapper.writeValueAsString(message);

            //mq只能发送string byte[] 和 serializable
            rabbitTemplate.convertAndSend("summary.queue",jsonMessage);

            //存储用户这一次发送的要求总结的内容，作为下次判断重复总结的依据。
            redisTemplate.opsForValue().set(lastKey,content,1, TimeUnit.MINUTES);

        }catch (Exception e){
            System.out.println("错误："+e.getMessage());
        }

        return message.getId();
    }

    @Override
    public String check(String id){

        String redisKey = "summary:"+id;

        String res = redisTemplate.opsForValue().get(redisKey);

        if(res != null){
            //返回结果
            return res;
        }

        return "processing";
    }

}
