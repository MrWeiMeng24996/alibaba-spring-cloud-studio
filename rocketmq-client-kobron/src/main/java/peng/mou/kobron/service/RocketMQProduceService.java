package peng.mou.kobron.service;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Service
public class RocketMQProduceService {

    public String produceService() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();

        return addr.getHostAddress()+addr.getHostName()+new Date();
    }
}
