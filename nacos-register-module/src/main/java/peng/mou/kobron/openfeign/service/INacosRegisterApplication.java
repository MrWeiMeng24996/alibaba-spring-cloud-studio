package peng.mou.kobron.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "pengmou-module-nacos-client")
@Service
public interface INacosRegisterApplication {

    @GetMapping("/pengMouNacosControllerDefaultMethod")
    String nacosRegisterApplicationDefaultMethod();
}
