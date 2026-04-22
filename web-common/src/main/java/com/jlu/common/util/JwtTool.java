package com.jlu.common.util;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.jlu.common.exception.UnauthorizedException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.time.Duration;
import java.util.Date;

@Component
@ConditionalOnProperty(name = "jlu.jwt.sharding", havingValue = "true")
public class JwtTool {
    private final JWTSigner jwtSigner;

    public JwtTool(KeyPair keyPair) {
        // 修正 1: Hutool 的 RS256 签名器需要明确传入算法 ID 和 KeyPair
        // 注意：算法名称通常大写 "RS256"
        this.jwtSigner = JWTSignerUtil.createSigner("RS256", keyPair);
    }

    /**
     * 创建 access-token
     */
    public String createToken(Long userId, Duration ttl) {
        // 修正 2: 链式调用时，sign() 内部不需要再传参数，因为前面已经 setSigner 了
        return JWT.create()
                .setPayload("user", userId)
                .setExpiresAt(new Date(System.currentTimeMillis() + ttl.toMillis()))
                .setSigner(jwtSigner)
                .sign();
    }

    /**
     * 解析token
     */
    public Long parseToken(String token) {
        // 1.校验token是否为空
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("未登录");
        }

        // 2.校验并解析jwt
        JWT jwt;
        try {
            // 修正 3: 使用 JWT.of(token) 后，必须 setSigner 才能进行后面的 verify
            jwt = JWT.of(token).setSigner(jwtSigner);
        } catch (Exception e) {
            throw new UnauthorizedException("无效的token", e);
        }

        // 3.校验签名是否合法
        if (!jwt.verify()) {
            throw new UnauthorizedException("无效的token");
        }

        // 4.校验时间是否过期
        try {
            // 修正 4: JWTValidator 校验的是已经解析好的 jwt 对象
            JWTValidator.of(jwt).validateDate();
        } catch (ValidateException e) {
            throw new UnauthorizedException("token已经过期");
        }

        // 5.提取数据
        Object userPayload = jwt.getPayload("user");
        if (userPayload == null) {
            throw new UnauthorizedException("无效的token");
        }

        // 6.数据解析转换
        try {
            return Long.valueOf(userPayload.toString());
        } catch (Exception e) {
            throw new UnauthorizedException("无效的token");
        }
    }
}