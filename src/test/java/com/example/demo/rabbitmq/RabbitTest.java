package com.example.demo.rabbitmq;

import com.example.demo.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTest {

    @Autowired
    private Producer producer;

    /**
     * 队列消息会被 接受者均匀的接收
     */
    @Test
    public void test() {
        producer.send("queue01","msg001");
        producer.send("queue01","msg002");
    }

    /**
     * 支持发送对象消息
     */
    @Test
    public void test02() {
        producer.send("queue01",new User("gh","123456"));
    }


    /**
     * 如何分析 topic 消息的绑定规则
     * 先看 consumer 看它消费哪个队列
     * 再查看 topicConfig ，看该队列和交换机的绑定规则 （routing-key）
     * 最后查看消息发送者
     */

    @Autowired
    private RabbitTemplate template;

    @Test
    public void test03() {
        // @RabbitListener(queues = "topic.messages") 接收到
//        template.convertSendAndReceive("exchange", "topic.a.b", "i am msg");


        // @RabbitListener(queues = "topic.messages")  + @RabbitListener(queues = "topic.message")
        template.convertSendAndReceive("exchange", "topic.message", "i am msg");
    }


    @Test
    public void test04() {
        template.convertAndSend("fanoutExchange","i am routingkey","msg01111");
    }
}
