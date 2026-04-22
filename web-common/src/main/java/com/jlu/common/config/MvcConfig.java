package com.jlu.common.config;

import com.jlu.common.interceptor.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@ConditionalOnClass(DispatcherServlet.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//添加拦截器
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        System.out.println(">>>>>> MvcConfig 被实例化了 <<<<<<");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println(">>>>>> 拦截器正在尝试注册 <<<<<<");
        registry.addInterceptor(new UserInfoInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error",
                        "/user/login",
                        "/user/register")
                .order(-1);
    }

}
