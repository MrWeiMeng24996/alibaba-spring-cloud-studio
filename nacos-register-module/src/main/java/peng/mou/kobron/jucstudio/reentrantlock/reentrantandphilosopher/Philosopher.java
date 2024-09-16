package peng.mou.kobron.jucstudio.reentrantlock.reentrantandphilosopher;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
   Chopsticks left;
    Chopsticks right;
    String name;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Philosopher(String name, Chopsticks left, Chopsticks right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @SneakyThrows
    @Override
    public void run() {
        condition.await();
        condition.signal();
        while(true){
            if(left.tryLock(3, TimeUnit.SECONDS)){
                try{
                    System.out.println(name+": 获取到了左筷子");
                    if(right.tryLock(3, TimeUnit.SECONDS)){
                        try{
                            System.out.println(name+": 获取到了右筷子");
                            System.out.println(name+": 开始吃饭");
                        }finally {
                            right.unlock();
                        }
                    }
                }finally {
                    left.unlock();
                }
            }
        }
    }
}
