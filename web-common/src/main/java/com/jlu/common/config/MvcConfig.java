package com.jlu.common.config;

import com.jlu.common.interceptor.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//添加拦截器 在springboot开启时自动启动 自动启动UserInfoInterceptor拦截器
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        System.out.println(">>>>>> MvcConfig 被实例化了 <<<<<<");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(new UserInfoInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error",
                        "/user/login",
                        "/user/register")
                .order(-1);
    }

}
