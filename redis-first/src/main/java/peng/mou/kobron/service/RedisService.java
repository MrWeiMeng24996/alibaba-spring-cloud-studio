package peng.mou.kobron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import peng.mou.kobron.entity.Person;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    public void setDataStructure(String hashName,String listName,String setName,String sortedName){
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name","pengmou");
        objectObjectHashMap.put("degree","bacher");
        objectObjectHashMap.put("age","28");
        redisTemplate.opsForValue().set(hashName,objectObjectHashMap);

        ArrayList<String> objects = new ArrayList<>();
        objects.add("SheDanny");
        objects.add("LinPiao");
        objects.add("HuangMan");
        redisTemplate.opsForValue().set(listName,objects);

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("set mou");
        hashSet.add("set Danny");
        hashSet.add("set Piao");
        redisTemplate.opsForValue().set(sortedName,hashSet);

        TreeSet<String> set = new TreeSet<>();
        set.add("sorted set");
        set.add("sorted mou");
        set.add("sorted danny");
        redisTemplate.opsForValue().set(setName,set);


    }

    public String getKeyValueByKobron(){
        System.out.println("time out");
        System.out.println(redisTemplate==null);
        Object name = redisTemplate.opsForValue().get("name");
        return name.toString();
    }

    public void setKeyValueByKobron(Integer age){
        redisTemplate.opsForValue().set("age", age);
    }

//    http://localhost:8080/setKeyValueByKobron/pengmou/26/male/30000.00
    public void setPersonByKobron(String name,Integer age,String gender,Double salary){
        Person person = new Person();
        person.setAge(age);
        person.setGender(gender);
        person.setName(name);
        person.setSalary(salary);
        redisTemplate.opsForValue().set(name,person);
    }

    public void setStringValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }
    public Object getStringValue(String key){
        Object o = redisTemplate.opsForValue().get(key);
        return o ;
    }
}
