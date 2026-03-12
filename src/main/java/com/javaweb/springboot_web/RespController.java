package com.javaweb.springboot_web;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RespController {
    @RequestMapping("/response")

    public void response(HttpServletResponse response) throws IOException {

//        设置状态码
        response.setStatus(401);

        //响应头
        response.setHeader("name","javaweb");

        //响应体
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("<hl>helloweb</hl>");

    }

    @RequestMapping("/response2")
    public ResponseEntity<String> response2(HttpServletResponse response ) throws IOException{

        return ResponseEntity.status(201).
                header("name","javaweb").
                body("<hl>hello response</hl>");


    }

}
