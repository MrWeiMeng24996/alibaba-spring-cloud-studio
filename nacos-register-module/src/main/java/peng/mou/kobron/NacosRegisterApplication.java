package peng.mou.kobron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosRegisterApplication {

    private static int i = 0;

    public static void main(String[] args) {
        SpringApplication.run(NacosRegisterApplication.class, args);

//        Thread thread = new Thread(() -> {
//            for (int j = 0; j < 100; j++) {
//                ++i;
//            }
//            System.out.println("thread1's i :"+i);
//        });
//
//        Thread thread2 = new Thread(() -> {
//            for (int j = 0; j < 100; j++) {
//                ++i;
//            }
//            System.out.println("thread2's i :"+i);
//        });
//        thread.start();
//        thread2.start();
//        System.out.println("i's value: "+i);
    }

}
