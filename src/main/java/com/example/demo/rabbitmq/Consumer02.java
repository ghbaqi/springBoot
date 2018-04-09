package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue01")
public class Consumer02 {


    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Consumer02 receive msg = "+msg);
    }
}
