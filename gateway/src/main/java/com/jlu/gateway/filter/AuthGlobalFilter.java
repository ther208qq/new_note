package com.jlu.gateway.filter;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.jlu.common.util.JwtTool;
import com.jlu.common.config.AuthProperties;
import com.jlu.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final JwtTool jwtTool;

    private final AuthProperties authProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();

        String token = null;
        List<String> headers = request.getHeaders().get("authorization");

        //获取token
        if(!CollectionUtils.isEmpty(headers)){
            token = headers.get(0);
        }

        if(isExclude(path)){
            return chain.filter(exchange);
        }

        Long userId;

        try{
            userId = jwtTool.parseToken(token);
            System.out.print("token解析成功："+userId);
        }catch(UnauthorizedException e){
            ServerHttpResponse response = exchange.getResponse();
            response.setRawStatusCode(401);
            System.out.println("错误原因："+e.getMessage());
            return response.setComplete();
        }

        //传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange ex = exchange.mutate()
                        .request(b->b.header("user-info",userInfo))
                                .build();
        
        return chain.filter(ex);
    }

    private boolean isExclude(String antpath) {
        List<String> excludePaths = authProperties.getExcludePaths();

        // 关键打印 1：看看配置到底读到没
        System.out.println("DEBUG: 当前配置文件中的白名单: " + excludePaths);
        // 关键打印 2：看看当前请求的路径长什么样
        System.out.println("DEBUG: 当前正在匹配的路径: " + antpath);

        if (CollectionUtils.isEmpty(excludePaths)) {
            return false;
        }

        for(String pathPattern:authProperties.getExcludePaths()){
            if(antPathMatcher.match(pathPattern,antpath)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
