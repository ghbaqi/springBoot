package com.example.demo.rabbitmq;

import com.example.demo.entity.User;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate template;

    public void send(String queueName, String msg) {
        System.out.println("Producer send msg = "+msg);
        template.convertSendAndReceive(queueName, msg);
    }


    public void send(String queueName, User user) {
        System.out.println("Producer send user = "+user);
        template.convertSendAndReceive(queueName, user);
    }
}
