package com.example;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restRedis {

	@Autowired
	private StringRedisTemplate template;
	
	@Autowired
	Tracer tracer;

	@Autowired
	private SpanAccessor accessor;

	static Logger log = Logger.getLogger(Demo2RedisMongoApplication.class.getName());
	Span newSpan1;
	
	@RequestMapping("/redis")
	public String redishello() throws Exception {
		newSpan1 = this.tracer.createSpan("redis", new AlwaysSampler());
		try {
			this.tracer.addTag("name", "redis-tag");
			log.info("redis service started ");
			
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "spring.boot.redis.test3";
		if (!this.template.hasKey(key)) {
			ops.set(key, "foo-Test-szjrrhszje");
		}
		System.out.println("Found key " + key + ", value=" + ops.get(key));
		return "success!!!!";
	}
		finally{
			newSpan1.logEvent("redis service closed ");
			this.tracer.close(newSpan1);
			
		}
	}
	
	
}
