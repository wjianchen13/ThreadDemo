package com.example.threaddemo.pool;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool4);
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
    public static void main(String[] args) throws InterruptedException {

    }

    /**
     * 开启线程
     * @param v
     */
    public void onTest1(View v) {
        ScheduledExecutorService pool= Executors.newScheduledThreadPool(2);
        for(int i=0;i<2;i++)
        {
            pool.scheduleAtFixedRate(new TargetTask(), 0, 500, TimeUnit.MILLISECONDS);
            //参数1： task任务
            //参数2： 首次执行任务的延迟时间
            //参数3： 周期性执行的时间
            //参数4： 时间单位

        }
//        Thread.sleep(3000);//主线程睡眠时间越长 周期次数越多
//        pool.shutdown();
    }


}