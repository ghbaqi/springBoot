package com.example.demo.rabbitmq;

import com.example.demo.entity.User;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue01")
public class Consumer {


    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Consumer receive msg = "+msg);
    }


    @RabbitHandler
    public void receive(User user) {
        System.out.println("Consumer receive user = "+user);
    }
}
