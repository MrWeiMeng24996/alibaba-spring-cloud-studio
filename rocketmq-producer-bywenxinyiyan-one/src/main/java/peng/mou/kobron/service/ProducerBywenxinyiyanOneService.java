package peng.mou.kobron.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerBywenxinyiyanOneService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String message) {
        // 发送同步消息
        rocketMQTemplate.convertAndSend(topic, message);

        // 或者发送异步消息
        // rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
        //     @Override
        //     public void onSuccess(SendResult sendResult) {
        //         // 处理发送成功逻辑
        //     }
        //
        //     @Override
        //     public void onException(Throwable e) {
        //         // 处理发送失败逻辑
        //     }
        // });
    }
}
