package com.example.demo.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，
 * 绑定了这个交换机的所有队列都收到这个消息
 */
@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue fanoutQueue01() {
        return new Queue("fanoutQueue.01");
    }

    @Bean
    public Queue fanoutQueue02() {
        return new Queue("fanoutQueue.02");
    }

    @Bean
    public Queue fanoutQueue03() {
        return new Queue("fanoutQueue.03");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    // 将交换机绑定到队列
    @Bean
    Binding binding01(Queue fanoutQueue01, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue01).to(fanoutExchange);
    }

    @Bean
    Binding binding02(Queue fanoutQueue02, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue02).to(fanoutExchange);
    }

    @Bean
    Binding binding03(Queue fanoutQueue03, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue03).to(fanoutExchange);
    }
}
