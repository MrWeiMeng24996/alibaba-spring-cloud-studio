package peng.mou.kobron.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.mou.kobron.service.ProducerBywenxinyiyanOneService;

import javax.annotation.Resource;

@RestController
public class ProducerBywenxinyiyanOneController {

    @Resource
    ProducerBywenxinyiyanOneService producerBywenxinyiyanOneService;
    @GetMapping("/sendMsg")
    public void sendMsg(){

        producerBywenxinyiyanOneService.sendMessage("rocketmq_producer_bywenxinyiyan_one","This msg by kobron and Wenxin yiyan");
    }
}
