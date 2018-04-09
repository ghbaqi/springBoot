package com.example.demo.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
 */
@Configuration
public class TopicConfig {

    private final static String QUEUE10 = "topic.message";
    private final static String QUEUE20 = "topic.messages";

    @Bean
    public Queue queue10() {
        return new Queue(QUEUE10);
    }

    @Bean
    public Queue queue20() {
        return new Queue(QUEUE20);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    // * 代表一个单词  # 代表多个单词

    /**
     * 绑定
     * queue      = topic.message
     * routingKey = topic.message
     */
    @Bean
    Binding bindingExchangeMessage10(Queue queue10, TopicExchange exchange) {
        return BindingBuilder.bind(queue10).to(exchange).with("topic.message");
    }

    /**
     * 绑定
     * queue      = topic.messages
     * routingKey =
     */
    @Bean
    Binding bindingExchangeMessage20(Queue queue20, TopicExchange exchange) {
        return BindingBuilder.bind(queue20).to(exchange).with("topic.*");
    }
}
