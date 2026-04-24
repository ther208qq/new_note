package com.jlu.auth.controller;

import com.jlu.auth.entity.User;
import com.jlu.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){

        String res = userService.login(user);
        if(!"fail".equals(res)){
            return res;
        }
        return "fail";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        String res = userService.register(user);

        if(res.equals("success")){
            return "success";
        }
        return "fail";
    }
    @GetMapping("/getnickname")
    public String getnickname(@RequestParam("username") String username){
        try{
            String res = userService.getnickname(username);
            System.out.println("返回结果是："+res);
            if(res!=null){
                return res;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
