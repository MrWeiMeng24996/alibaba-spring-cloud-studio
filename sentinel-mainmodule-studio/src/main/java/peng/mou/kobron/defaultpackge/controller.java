package peng.mou.kobron.defaultpackge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @GetMapping("/testA")
    public String TestA(){

        return "This is TestA";
    }

    @GetMapping("/testB")
    public String TestB(){

        return "This is TestB";
    }
}
