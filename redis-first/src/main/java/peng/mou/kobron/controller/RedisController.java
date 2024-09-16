package peng.mou.kobron.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import peng.mou.kobron.service.RedisService;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    RedisService redisService;

    @GetMapping("/setDataStructure/{hashName}/{listName}/{setName}/{sortedName}")
    public String setDataStructure(@PathVariable String hashName,@PathVariable String listName,@PathVariable String setName,@PathVariable String sortedName) {
        redisService.setDataStructure( hashName, listName, setName, sortedName);
        return "setDataStructure success";
    }

        @GetMapping("/setStringValue/{key}/{value}")
    public void setStringValue(@PathVariable String key,@PathVariable String value){
        redisService.setStringValue(key,value);
    }

    @GetMapping("/getStringValue/{key}")
    public Object getStringValue(@PathVariable String key){
        Object o = redisService.getStringValue(key);
        return o ;
    }

    @GetMapping("/getKeyValueByKobron")
    public String getKeyValueByKobron(){
        return redisService.getKeyValueByKobron();
    }

    @GetMapping("/setKeyValueByKobron/{age}")
    public String setKeyValueByKobron(@PathVariable Integer age){
        redisService.setKeyValueByKobron(age);
        return "chengong";
    }

    @GetMapping("/setKeyValueByKobron/{name}/{age}/{gender}/{salary}")
    public String setPersonByKobron(@PathVariable String name,@PathVariable Integer age,@PathVariable String gender,@PathVariable Double salary){
        redisService.setPersonByKobron( name, age, gender, salary);
        return "success:setPersonByKobron";
    }
}
