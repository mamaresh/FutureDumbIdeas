package com.rare.server.controller.starter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({ "com.rare.server.*" })
public class Rare {

	public static final Logger LOG = LoggerFactory.getLogger(Rare.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Rare.class, args);
		LOG.info("Application Started...");

		LOG.debug("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			LOG.debug(beanName);
		}
	}

}
