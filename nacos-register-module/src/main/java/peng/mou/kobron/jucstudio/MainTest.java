package peng.mou.kobron.jucstudio;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


class TestLiveLock {
    static volatile int count = 10;

    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
// 期望减到 0 退出循环
            while (count > 0) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println("count: {}"+ count);
            }
        }, "t1").start();
        new Thread(() -> {
// 期望超过 20 退出循环
            while (count < 20) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println("count: {}"+ count);
            }
        }, "t2").start();
    }
}

class LiveLock{

    static volatile int i = 8;
    static final Object obj = new Object();
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (i >= 0) {
                    --i;
            }
        });

        Thread thread2 = new Thread(() -> {
            while (i <= 0) {
                    ++i;
            }
        });

        thread.start();
        thread2.start();
    }
}

class MainTest3{

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
            }
        });

        Thread threadWait = new Thread(() -> {

            synchronized (MainTest3.class){}
            try {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(i);
                }
                MainTest3.class.wait();
                for (int i = 0; i < 1000; i++) {
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadWait.start();

        System.out.println("即将打断wait 方法");
//        thread.interrupt();
//        thread.start();
//
////        thread.join();
//
//        System.out.println("即将打断join");
//        thread.interrupt();

    }
}

public class MainTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
            int i = 0;
            for (int j = 0; j < 50; j++) {
                System.out.println(i+": Thread tiu");
                Thread.sleep(200);
                ++i;
            }
            return  i;
        });

        Thread tiu = new Thread(integerFutureTask, "tiu");
        tiu.start();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(24);
            System.out.println(i+": Thread MainTest");
        }
        System.out.println("主线程的循环语句执行完毕了。。");

        /**
         * 我的理解是只要执行这句话主线程就会发生阻塞？
         * */
        System.out.println("即将阻塞等待了。。。。。。。。。。。。。。。。。。。。。。。");
        Integer integer = integerFutureTask.get();

        System.out.println("阻塞等待结束。。。。。。。。。。。。。。。。。。。。");
        System.out.println("This is integer' value :"+integer);
    }
}

/**
 * 测试可见性问题
 * */
class MainTest2{
//     static int i ;
//    volatile static int i ;
    static int i ;
    static final Object tt = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (tt){

                        ++i;
                }
            }

        });

        Thread thread2 = new Thread(() -> {

                for (int j = 0; j < 200; j++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (tt){
                    --i;
                }
            }
        });

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println("This is i's value :"+i);
    }

}