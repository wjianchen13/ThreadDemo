package com.example.threaddemo.pool;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 调度器的钩子方法
 */
public class ThreadPoolActivity6 extends AppCompatActivity {

    public static final int SLEEP_GAP=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool6);
    }

    static class TargetTask implements Runnable{

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
            System.out.println(taskName + " end...");
        }
    }

    /**
     * 开启线程
     * @param v
     */
    public void onTest1(View v) {
        ExecutorService pool=new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2)){
            @Override
            protected void terminated() {
                System.out.println("调度器已停止...");
            }

            @Override
            protected void beforeExecute(Thread t,Runnable target) {
                System.out.println("前钩执行...");
                super.beforeExecute(t, target);
            }

            @Override
            protected void afterExecute(Runnable target,Throwable t) {
                System.out.println("后钩执行...");
                super.afterExecute(target, t);
            }
        };
        for(int i = 0; i < 5; i ++)
            pool.execute(new TargetTask());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

}