package com.example.threaddemo.pool;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool3);
    }

    public static final int SLEEP_GAP=1000;
    static class TargetTask implements Runnable{
        static AtomicInteger taskNo=new AtomicInteger(1);
        private String taskName;
        public TargetTask()
        {
            taskName="task-"+taskNo;
            taskNo.incrementAndGet();
        }
        public void run()
        {
            System.out.println(taskName+" is doing...");
            try {
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(taskName+" end...");
        }
    }

    /**
     * 启动线程
     * @param v
     */
    public void onTest1(View v) {
        ExecutorService pool= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++)
        {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }
        pool.shutdown();
    }

}