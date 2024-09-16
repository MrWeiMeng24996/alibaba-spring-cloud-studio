package peng.mou.kobron.jucstudio.threadpoolstudio2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest {
}

class ThreadPool extends Thread{
//    4. 存放任务的队列的queue
    private BlockingQueue<Runnable> queue;
//    1.线程核心数
    private int coreSize;
    private HashSet<Runnable> hashSet = new HashSet<>();
//    3.execute 方法
    public void execute(Runnable task){

    }

    @Override
    public void run() {
    }

    class Worker{

    }
}

class BlockingQueue<T>{
    //1.有个双向队列
    private Deque<T> deque = new ArrayDeque<>();
    //2.有个queue的最大容量
    private int capcity;
    //    5.需要一把锁
    private ReentrantLock lock = new ReentrantLock();
    //    6.需要两个条件变量
    private Condition fullWaitSet = lock.newCondition();
    private Condition emptyWaitSet = lock.newCondition();
    //    3.有个put方法，往queue里面添加任务
    public void put(T element){
        lock.lock();
        try{
            while(deque.size()==capcity){
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(element);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }
    //    4.有个消费的方法，消费queue中的任务
    public T take(){
        lock.lock();
        try {
            while(deque.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }
}