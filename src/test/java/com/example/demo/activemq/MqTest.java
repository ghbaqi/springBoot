package com.example.demo.activemq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 消息队列中间件是分布式系统中重要的组件，主要解决应用耦合，异步消息，流量削锋等问题。
 * 实现高性能，高可用，可伸缩和最终一致性架构。
 * 是大型分布式系统不可缺少的中间件。
 * <p>
 * 目前在生产环境，使用较多的消息队列有ActiveMQ，RabbitMQ，ZeroMQ，Kafka，MetaMQ，RocketMQ等。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {

    @Autowired
    private Producer producer;

    @Test
    public void test01() {
        producer.sendMsg("ghbaqi hi", "queue01");
    }

    @Autowired
    private Publisher publisher;

    @Test
    public void test02() {
        publisher.publish("topic01","ghbaqi");
    }
}
