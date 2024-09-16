package peng.mou.kobron.jucstudio.mi;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程1、2、3，分别输入a,b,c 5次，但是要按照abc abc abc abc abc 的顺序
 * */
@Slf4j
public class MainTest {
    static String flag = "t3";
    static final Object obj = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (obj) {
                    while (flag.equals("t3")) {
                        log.debug("a");
                        flag = "t1";
                        obj.notifyAll();
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (obj) {
                    while (flag.equals("t1")) {
                        log.debug("b");
                        flag = "t2";
                        obj.notifyAll();
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                synchronized (obj) {
                    while (flag.equals("t2")) {
                        log.debug("c");
                        flag = "t3";
                        obj.notifyAll();
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("====================");
        }, "t3").start();
    }
}
