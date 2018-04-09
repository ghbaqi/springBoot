package com.example.demo.rabbitmq.fanout;

import com.example.demo.entity.User;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanoutQueue.01")
public class FanoutConsumer01 {


    @RabbitHandler
    public void receive(String msg) {
        System.out.println("FanoutConsumer01 receive msg = "+msg);
    }


    @RabbitHandler
    public void receive(User user) {
        System.out.println("FanoutConsumer01 receive user = "+user);
    }
}
