package peng.mou.kobron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMainStudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelMainStudioApplication.class, args);
    }

}
