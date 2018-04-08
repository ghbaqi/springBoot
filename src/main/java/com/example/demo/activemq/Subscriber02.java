package com.example.demo.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Subscriber02 {

    @JmsListener(destination = "topic01",containerFactory = "myJmsContainerFactory")
    public void subscribe(String msg) {
        System.out.println("subscribe02 msg = "+msg);
    }
}
