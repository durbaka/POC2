package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@PropertySource("application.properties")
public class SpringSessionRedisConfiguration  {

	@Value("${spring.redis.host}")
	private String redisHostName;

	@Value("${spring.redis.port}")
	private int redisPort;

	
	
	@Autowired
	@Bean
	RedisConnectionFactory redisConnectionFactory() {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(redisHostName);
		factory.setPort(redisPort);
		factory.setUsePool(true);
		return factory;
	}

	@Autowired
	@Bean
	StringRedisTemplate redisTemplate() {
		StringRedisTemplate stringredisTemplate = new StringRedisTemplate();
		stringredisTemplate.setConnectionFactory(redisConnectionFactory());
		return stringredisTemplate;
	}

	

}
