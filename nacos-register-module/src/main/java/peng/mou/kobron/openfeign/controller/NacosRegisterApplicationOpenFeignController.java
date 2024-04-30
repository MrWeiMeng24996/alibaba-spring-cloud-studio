package peng.mou.kobron.openfeign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.mou.kobron.openfeign.service.INacosRegisterApplication;

import javax.annotation.Resource;

@RestController
public class NacosRegisterApplicationOpenFeignController {

    @Resource
    INacosRegisterApplication iNacosRegisterApplication;

    //    INacosRegisterApplication nacosRegisterApplication;
    @GetMapping("/nacosRegisterApplicationOpenFeignControllerMethod")
    public String nacosRegisterApplicationOpenFeignControllerMethod() {

        return iNacosRegisterApplication.nacosRegisterApplicationDefaultMethod();
    }
}
