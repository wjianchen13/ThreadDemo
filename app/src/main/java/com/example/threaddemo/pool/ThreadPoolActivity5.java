package com.example.threaddemo.pool;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadFactory
 */
public class ThreadPoolActivity5 extends AppCompatActivity {

    public static final int SLEEP_GAP=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool5);
    }

    /**
     * 开启线程
     * @param v
     */
    public void onTest1(View v) {
        ExecutorService pool = Executors.newFixedThreadPool(2,new SimpleThreadFactory());
        // ExecutorService pool=Executors.newFixedThreadPool(2);
        for(int i = 0; i < 5; i ++) {
            pool.submit(new TargetTask());
        }
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

    static class TargetTask implements Runnable {

        static AtomicInteger taskNo = new AtomicInteger(1);
        String taskName;

        public TargetTask() {
            taskName="task-"+taskNo;
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
            ThreadGroup myGroup = new ThreadGroup("MyThreadGroup");
            Thread thread = new Thread(myGroup, task, threadName);
            thread.setDaemon(true);
            thread.setPriority(1);
            return thread;
        }
    }

}