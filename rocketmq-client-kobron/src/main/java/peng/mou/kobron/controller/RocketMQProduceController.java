package peng.mou.kobron.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.mou.kobron.service.RocketMQProduceService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RestController
public class RocketMQProduceController {
    @Resource
    private RocketMQProduceService rocketMQProduceService;

    @GetMapping("/produceController")
    public String produceController() throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {

        DefaultMQProducer firstTestProduceGroup = new DefaultMQProducer("The_first_test_Produce_Group_byKobron");
        firstTestProduceGroup.setNamesrvAddr("localhost:9876");
        firstTestProduceGroup.start();
        for (int i = 0; i < 100; i++) {
            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ Beijing" + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );   //（3）
            // 利用producer进行发送，并同步等待发送结果
            SendResult sendResult = firstTestProduceGroup.send(msg);   //（4）
            System.out.printf("%s%n", sendResult);
        }
        // 一旦producer不再使用，关闭producer
        firstTestProduceGroup.shutdown();

        return "sucess";
    }
}
