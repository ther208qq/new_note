package com.javaweb.springboot_web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.javaweb.springboot_web", "com.jlu.common"})
public class SpringbootWebApplication {


	public static void main(String[] args) {
		//获取静态Bean
		ConfigurableApplicationContext ctx= SpringApplication.run(SpringbootWebApplication.class, args);

		RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
	}

}
