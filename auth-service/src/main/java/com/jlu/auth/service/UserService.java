package com.jlu.auth.service;

import com.jlu.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ther
* @description 针对表【user】的数据库操作Service
* @createDate 2026-04-13 21:15:04
*/
public interface UserService extends IService<User> {
    public String login(User user);

    public String register(User user);

    public String getnickname(String username);
}
