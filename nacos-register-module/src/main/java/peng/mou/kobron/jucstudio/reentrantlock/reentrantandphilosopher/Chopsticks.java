package peng.mou.kobron.jucstudio.reentrantlock.reentrantandphilosopher;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

@Data
public class Chopsticks extends ReentrantLock {
    private String number;

    public Chopsticks(String number){
        this.number = number;
    }
}
