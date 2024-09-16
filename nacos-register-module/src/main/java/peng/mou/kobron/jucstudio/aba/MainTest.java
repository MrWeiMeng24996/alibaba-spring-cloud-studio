package peng.mou.kobron.jucstudio.aba;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.function.Function;
import java.util.function.Supplier;

@Data
class Student{
    private String name;
    private Integer age;


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@Slf4j
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        Function<Integer,Integer> function = x->x+88;
        Integer apply = function.apply(99);

        Student stu = new Student();
        stu.setName("A");
        AtomicStampedReference<Student> atomicStampedReference = new AtomicStampedReference<>(stu,0);
        int stamp1 = atomicStampedReference.getStamp();

         new Thread(() -> {
            Student reference = atomicStampedReference.getReference();
             Student student = new Student();
             student.setName("B");
            int stamp = atomicStampedReference.getStamp();
            atomicStampedReference.compareAndSet(reference,student,stamp,stamp+1);
        }).start();

        new Thread(() -> {
            Student reference = atomicStampedReference.getReference();
            Student student = new Student();
            student.setName("A");
            int stamp = atomicStampedReference.getStamp();
            atomicStampedReference.compareAndSet(reference,student,stamp,stamp+1);
        }).start();

        Thread.sleep(1500);

        new Thread(() -> {
            Student reference = atomicStampedReference.getReference();
            Student student = new Student();
            student.setName("C");
            atomicStampedReference.compareAndSet(reference,student,stamp1,stamp1+1);
        }).start();


        log.debug(String.valueOf(stu));
        log.debug(String.valueOf(atomicStampedReference.getReference()));
    }
}