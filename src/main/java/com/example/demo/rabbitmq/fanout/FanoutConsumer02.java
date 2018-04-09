package com.example.demo.rabbitmq.fanout;

import com.example.demo.entity.User;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanoutQueue.02")
public class FanoutConsumer02 {


    @RabbitHandler
    public void receive(String msg) {
        System.out.println("FanoutConsumer02 receive msg = "+msg);
    }


    @RabbitHandler
    public void receive(User user) {
        System.out.println("FanoutConsumer02 receive user = "+user);
    }
}
