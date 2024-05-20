package com.example.threaddemo.pool;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolActivity1 extends AppCompatActivity {

    public static final int SLEEP_GAP = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool1);
    }

    static class TargetTask implements Runnable {

        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;

        public TargetTask() {
            taskName="task-" + taskNo;
            taskNo.incrementAndGet();
        }

        public void run() {
            System.out.println("task:" + taskName + " is doing...");
            try {
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task:" + taskName + " end...");
        }
    }

    /**
     *
     * @param v
     */
    public void onTest1(View v) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 3; i ++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }
        pool.shutdown();
    }

}