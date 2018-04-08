package com.example.demo.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)  // maxInactiveIntervalInSeconds: 设置Session失效时间
public class RedisSessionConfig {
}
