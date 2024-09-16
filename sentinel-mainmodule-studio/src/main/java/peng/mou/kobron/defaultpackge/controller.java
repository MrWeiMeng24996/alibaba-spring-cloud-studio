package peng.mou.kobron.defaultpackge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.mou.kobron.defaultpackge.service.DefaultService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class controller {

    @Autowired
    private DefaultService defaultService;

    @GetMapping("/testA")
    public @ResponseBody HttpServletResponse  TestA(HttpServletResponse response )  throws IOException {
       return defaultService.defaultMethod(response);

    }

    @GetMapping("/testC")
    private String TestC(){
//        defaultService.defaultMethod();
        return "This is TestA";
    }

    @GetMapping("/testB")
    public String TestB(){

        return "This is TestB";
    }
}
