package com.example.demo.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;

//@Component
public class Producer {

//    @Resource
    private JmsMessagingTemplate template;

    public void sendMsg(String msg, String destination) {
        System.out.println("sendMsg msg = "+msg);
        ActiveMQQueue queue = new ActiveMQQueue(destination);
        template.convertAndSend(queue,msg);
    }
}
