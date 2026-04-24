package com.jlu.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jlu.auth.entity.User;
import com.jlu.auth.service.UserService;
import com.jlu.auth.mapper.UserMapper;
import com.jlu.common.util.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
* @author ther
* @description 针对表【user】的数据库操作Service实现
* @createDate 2026-04-13 21:15:04
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final JwtTool jwtTool;

    @Override
    public String login(User user) {

        //使用lambdaQuery查询
        User temp = this.lambdaQuery()
                .eq(User::getUsername,user.getUsername())
                .one();
        //判断有没有该账号
        if(temp!=null){
            //判断密码对不对
            if(temp.getPassword().equals(user.getPassword())){
                Long userId = temp.getId();
                return jwtTool.createToken(userId, Duration.ofDays(1));
            }
        }
        return "fail";
    }

    @Override
    public String register(User user) {
        boolean res = this.save(user);
        if(res){
            return "success";
        }
        return "fail";
    }

    @Override
    public String getnickname(String username) {

        User user = this.lambdaQuery()
                .select(User::getNickname)
                .eq(User::getUsername,username)
                .one();
        String nickname = (user != null) ? user.getNickname() : null;

        return nickname;
    }

}




