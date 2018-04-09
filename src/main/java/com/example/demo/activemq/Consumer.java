package com.example.demo.activemq;

//@Component
public class Consumer {

//    @JmsListener(destination = "queue01")
    public void receive(String msg) {
        System.out.println("receive msg = "+msg);
    }
}
