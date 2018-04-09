package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue01() {
        return new Queue("queue01");
    }

    @Bean
    public Queue queue02() {
        return new Queue("queue02");
    }
}
