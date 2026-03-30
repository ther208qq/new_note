package com.javaweb.springboot_web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootWebApplicationTests {

//	@Test
//	void contextLoads() {
//	}

//	自动注入
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	void redisConntionText(){
//		redis操作
//		key:value形式
		redisTemplate.opsForValue().set("test_content","hello_redis");

		String value = redisTemplate.opsForValue().get("test_content");
		System.out.println("测试结果为:"+value);
	}



}
