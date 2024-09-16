package peng.mou.kobron.jucstudio.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MainTest {
    public static void main(String[] args) throws InterruptedException {

        AotAccount aotAccount = new AotAccount();
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                aotAccount.increase(100);
            }
        }, "t3");

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                aotAccount.decrease(100);
            }
        }, "t4");

        t3.start();
        t4.start();
        t3.join();
        t4.join();
        log.debug("This is aotAccount's money :"+String.valueOf(aotAccount.money));
        log.debug("=============================================================================================");
        Account account = new Account();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.increase(100);
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.decrease(100);
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("This is account's money :"+String.valueOf(account.money));
    }
}

@Slf4j
class Account{
    volatile int money;

    public void increase(int salary){
        money+=salary;
    }

    public void decrease(int cost){
        money -= cost;
    }
}

@Slf4j
class AotAccount{
    AtomicInteger money = new AtomicInteger(1000);
    public void increase(int salary){
        while(true){
            int i = money.get();
            int fin = i+salary;
            if (money.compareAndSet(i,fin)){
                break;
            }
        }
    }
    public void decrease(int cost){
       money.getAndUpdate(money->money-cost);
    }
}