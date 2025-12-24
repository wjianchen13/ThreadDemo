package com.example.threaddemo.pool;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池的拒绝策略
 */
public class ThreadPoolActivity7 extends AppCompatActivity {

    public static final int SLEEP_GAP=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool7);
    }

    static class TargetTask implements Runnable {

        static AtomicInteger taskNo = new AtomicInteger(1);
        String taskName;

        public TargetTask() {
            taskName = "task-"+taskNo;
            taskNo.incrementAndGet();
        }

        public void run() {
            System.out.println(Thread.currentThread().getName()+": "+taskName+" is doing...");
            try {
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(taskName+" end...");
        }
    }

    static class SimpleThreadFactory implements ThreadFactory {

        static AtomicInteger threadNo = new AtomicInteger(1);

        public Thread newThread(Runnable task) {
            String threadName = "simpleThread-" + threadNo;
            System.out.println("创建一条线程，名字是：" + threadName);
            threadNo.incrementAndGet();
            Thread thread = new Thread(task, threadName);
            thread.setDaemon(true);
            return thread;
        }
    }
    static class CustomerIgnorePolicy implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(Thread.currentThread().getName()+"-rejected;  taskCount-"+executor.getTaskCount());
        }
    }

    /**
     * 开启线程
     * @param v
     */
    public void onTest1(View v) {
        int corePoolSize = 2;//核心线程数
        int maximumPoolSize = 4;//最大线程数
        long keepAlive = 10;//空闲时间
        TimeUnit unit= TimeUnit.SECONDS;//时间单位
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);//阻塞队列
        ThreadFactory factory = new SimpleThreadFactory();//自定义线程工厂
        RejectedExecutionHandler policy = new CustomerIgnorePolicy();//自定义拒绝策略
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAlive,unit,workQueue,factory,policy);

        pool.prestartAllCoreThreads();
        for(int i = 0; i < 11; i ++)
            pool.execute(new TargetTask());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

}