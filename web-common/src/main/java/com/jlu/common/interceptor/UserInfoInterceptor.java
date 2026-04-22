package com.jlu.common.interceptor;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import com.jlu.common.util.UserContext;


//用户拦截器
public class UserInfoInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{


        String userInfo = request.getHeader("user-info");

        System.out.println("拦截器收到路径: " + request.getRequestURI());
        System.out.println("==== 拦截器收到的 Header: " + userInfo + " ====");

        if(StrUtil.isNotBlank(userInfo)){
            Long userId = Long.valueOf(userInfo);
            UserContext.setUserId(userId);
            System.out.println("存储信息到UserContext");
            return true;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
        UserContext.removeUser();
    }

}
