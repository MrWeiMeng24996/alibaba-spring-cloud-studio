package peng.mou.kobron.entity;

import lombok.Data;

@Data
public class Person {
    String name;
    Integer age;
    String gender;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                '}';
    }

    Double salary;
}
