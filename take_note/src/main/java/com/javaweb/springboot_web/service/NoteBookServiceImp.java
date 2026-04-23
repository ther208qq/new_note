package com.javaweb.springboot_web.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jlu.common.util.UserContext;
import com.javaweb.springboot_web.client.UserClient;
import com.javaweb.springboot_web.mapper.NoteBookMapper;

import com.javaweb.springboot_web.pojo.NoteBook;
import lombok.RequiredArgsConstructor;

import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;

//Service 无需编写任何service代码
@Service
@RequiredArgsConstructor //什么构造器
public class NoteBookServiceImp extends ServiceImpl<NoteBookMapper,NoteBook> implements NoteBookService {

    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate;

    private final UserClient userClient;

    public String getUserNickname(String username) {

        //在service层通过用户信息进行业务操作
        Long userId = UserContext.getUserInfo();

        //调用auth-service微服务
        String res = userClient.queryNicknameByUsername(username);
        if(res == null){
            return "默认昵称";
        }
        else{
            return res;
        }

    }

}
