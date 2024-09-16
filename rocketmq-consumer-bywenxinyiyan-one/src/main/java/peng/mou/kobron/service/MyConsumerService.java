package peng.mou.kobron.service;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "TopicTest", consumerGroup = "my-consumer-group", consumeMode = ConsumeMode.ORDERLY)
public class MyConsumerService implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        // 在这里处理你的消息
        System.out.println("Wenxinyiyan Received message: " + message);
    }
}