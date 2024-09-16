package peng.mou.kobron.jucstudio.waitnotify;


public class MainTest {
    final static Object obj = new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (obj){
                for (int i = 0; i < 10; i++) {
                    System.out.println("模拟耗时操作。。。"+i);
                    if(i==5) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t1 is running...");
            }
        },"t1").start();

        new Thread(()->{
            synchronized (obj){
                System.out.println("t2 is running...");
                obj.notify();
            }
        },"t2").start();
    }
}
