package com.jlu.common.interceptor;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import com.jlu.common.util.UserContext;


//用户拦截器 需要配置类进行配合
public class UserInfoInterceptor implements HandlerInterceptor {


    //在controller方法调用之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        //获取请求头的id
        String userInfo = request.getHeader("user-info");

        System.out.println("==== 拦截器收到的 Header: " + userInfo + " ====");

        //非空，就把信息存到UserContext
        if(StrUtil.isNotBlank(userInfo)){
            Long userId = Long.valueOf(userInfo);
            UserContext.setUserId(userId);
            System.out.println("存储信息到UserContext");
            return true;
        }
        return false;
    }

    //请求结束移除信息
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
        UserContext.removeUser();
    }

}
