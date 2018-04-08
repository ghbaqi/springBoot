package com.example.demo.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "queue01")
    public void receive(String msg) {
        System.out.println("receive msg = "+msg);
    }
}
