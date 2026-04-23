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

/*这个网关拦截器负责处理请求，判断是否是白名单。主要是负责非白名单的。解析请求头的token，如果解析成功，得到id，那就把id封装传递到下游服务。*/


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

        //获取请求头
        String token = null;
        List<String> headers = request.getHeaders().get("authorization");

        //获取token
        if(!CollectionUtils.isEmpty(headers)){
            token = headers.get(0);
        }

        //处理白名单
        if(isExclude(path)){
            return chain.filter(exchange);
        }

        //非白名单，解析token
        Long userId;

        try{
            //使用jwt工具解析token,解析成功才能进行下一步
            userId = jwtTool.parseToken(token);

            //传递用户信息
            String userInfo = userId.toString();
            ServerWebExchange ex = exchange.mutate()
                    .request(b->b.header("user-info",userInfo))
                    .build();

            return chain.filter(ex);

        }catch(UnauthorizedException e){
            ServerHttpResponse response = exchange.getResponse();
            response.setRawStatusCode(401);
            System.out.println("错误原因："+e.getMessage());
            return response.setComplete();
        }

    }

    private boolean isExclude(String antpath) {
        //获取到路径
        List<String> excludePaths = authProperties.getExcludePaths();

        System.out.println("DEBUG: 当前配置文件中的白名单: " + excludePaths);

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
