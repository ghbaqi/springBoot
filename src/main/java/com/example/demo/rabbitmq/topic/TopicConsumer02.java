package com.example.demo.rabbitmq.topic;

import com.example.demo.entity.User;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messages")
public class TopicConsumer02 {


    @RabbitHandler
    public void receive(String msg) {
        System.out.println("TopicConsumer02 receive msg = "+msg);
    }


    @RabbitHandler
    public void receive(User user) {
        System.out.println("TopicConsumer02 receive user = "+user);
    }
}
