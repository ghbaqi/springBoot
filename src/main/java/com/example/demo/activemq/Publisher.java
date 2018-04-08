package com.example.demo.activemq;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

@Component
public class Publisher {

    @Resource
    private JmsMessagingTemplate template;

    public void publish(String destination, String msg) {
        System.out.println("publish msg = "+msg);
        ActiveMQTopic topic = new ActiveMQTopic(destination);
        template.convertAndSend(topic,msg);
    }

    @Bean
    JmsListenerContainerFactory myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }


}
