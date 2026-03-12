//package com.javaweb.springboot_web;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Controller {
//
//    @RequestMapping("/request")
//    public String request(HttpServletRequest request){
////        System.out.println("hello");
////
////        return "hello" + name;
//
//        String name = request.getParameter("name");
//        String age = request.getParameter("age");
//        System.out.println(name+age);
//
//
//        String uri = request.getRequestURI();
//        String url = request.getRequestURL().toString();
//        System.out.println("uri:"+uri);
//        System.out.println("url:"+url);
//
//
//        String method = request.getMethod();
//        System.out.println("method:"+method);
//
//        String header = request.getHeader("user-agent");
//        System.out.println("header:"+header);
//
//        return "request sucess";
//
//    }
//
//}
