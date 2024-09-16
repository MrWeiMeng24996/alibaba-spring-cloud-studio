package peng.mou.kobron.jucstudio.threadpoolstudio;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        ThreadPool threadPool = new ThreadPool(8,3,TimeUnit.MILLISECONDS,8);

//        new Thread(()->{
//            for (int i = 0; i < 24; i++) {
//                int finalI = i;
//                threadPool.execute(()->{
//                    log.debug("这是谋哥手撸的线程池 测试线程2：{}", finalI);
//                });
//            }
//        },"测试线程2").start();

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            threadPool.execute(()->{
                log.debug("这是谋哥手撸的线程池：{}", finalI);
            });
        }
    }
}

@Slf4j
class ThreadPool{
    //多余的线程就放在这里：任务队列
    private BlockingQueue<Runnable> taskQueue;

    //正在执行的线程：线程集合
    private final HashSet<Worker> workers = new HashSet<>();

    //线程池总得有个核心线程数吧
    private final int coreSize;
    //如果一直没有线程产生就得停止执行吧，所以得有一个超时时间
    private long timeout;
    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapcity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(queueCapcity);
    }

    public void execute(Runnable task){
        synchronized (workers){
            if(workers.size()<=coreSize){
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            }else{
                taskQueue.put(task);
            }
        }
    }
    class Worker extends Thread{
        private Runnable task;
        public Worker(Runnable task){
            this.task = task;
        }

        @Override
        public void run() {
//            while(task!=null|| (task=taskQueue.poll(timeout,TimeUnit.MILLISECONDS))!=null){
            while(task!=null|| (task=taskQueue.take())!=null){
                try {
                    task.run();
                }catch (Exception e){
                    log.debug(String.valueOf(e));
                }finally {
                    task = null;
                }
            }
            synchronized (workers){
                workers.remove(this);
            }
        }
    }
}

//需要使用泛型
@Slf4j
class BlockingQueue<T>{
    //1. 任务队列
    private Deque<T> queue = new ArrayDeque<>();
    //2. 创建一把锁：因为会有多个消费者和生产者操作这个任务队列
    private ReentrantLock lock = new ReentrantLock();
    //3. 既然创建了锁自然需要多个条件变量的支持撒，因为毕竟有生产者和消费者这俩角色
    private Condition fullWaitSet = lock.newCondition();
    private Condition emptyWaitSet = lock.newCondition();
    //4. 容量上限
    private int capcity;


    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //5. put 线程的方法
    public void put(T element){
        lock.lock();
        try {
            while(queue.size()==capcity){
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //6. take 消费线程的方法
    public T take(){
        lock.lock();
        try {
            while(queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
           fullWaitSet.signal();
           return t;
        }finally {
            lock.unlock();
        }
    }
    //7. 含有超时等待消费线程的方法poll
    public T poll(long timeout, TimeUnit timeUnit){
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);

            while(queue.isEmpty()){
                try {
                    if(nanos<=0){
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //8. 获取大小
    public int size(){
        lock.lock();
        try {
            return capcity;
        }finally {
            lock.unlock();
        }
    }
}