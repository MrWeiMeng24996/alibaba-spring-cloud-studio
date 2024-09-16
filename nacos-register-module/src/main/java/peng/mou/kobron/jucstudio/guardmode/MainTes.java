package peng.mou.kobron.jucstudio.guardmode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainTes {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(() -> {
            System.out.println("开始运行副线程/。。。。");
            Thread.sleep(3000);
            return 88;
        });
        new Thread(futureTask).start();
        System.out.println("现在开始主线程的运行");
        System.out.println("主线程照常运行。。。。");
        System.out.println("副线程获取值");
        Object o = futureTask.get();
        System.out.println("获取的值"+o.toString());
    }
}
