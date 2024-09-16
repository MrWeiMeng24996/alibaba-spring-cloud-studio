package peng.mou.kobron.jucstudio.reentrantlock.philosopher;

import lombok.SneakyThrows;

public class Philosopher extends Thread {
    Chopsticks left;
    Chopsticks right;
    String name;

    public Philosopher(String name,Chopsticks left,Chopsticks right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @SneakyThrows
    @Override
    public void run() {
        while(true){
            synchronized (left){
                System.out.println(name+": 拿到了左手筷子.....");
                Thread.sleep(200);
                synchronized (right){
                    System.out.println(name+": 拿到了右手筷子.....");
                    System.out.println(name+": 正在吃饭.....");
                }
            }
        }

    }
}
