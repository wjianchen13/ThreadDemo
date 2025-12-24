package com.example.threaddemo.pool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

public class ThreadPoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
    }

    /**
     * newSingleThreadExecutor
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, ThreadPoolActivity1.class));
    }

    /**
     * newFixedThreadPool
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, ThreadPoolActivity2.class));
    }

    /**
     * newCachedThreadPool
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, ThreadPoolActivity3.class));
    }

    /**
     * newScheduledThreadPool
     * @param v
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, ThreadPoolActivity4.class));
    }

    /**
     * ThreadFactory
     * @param v
     */
    public void onTest5(View v) {
        startActivity(new Intent(this, ThreadPoolActivity5.class));
    }

    /**
     * 调度器的钩子方法
     * @param v
     */
    public void onTest6(View v) {
        startActivity(new Intent(this, ThreadPoolActivity6.class));
    }

    /**
     * 线程池的拒绝策略
     * @param v
     */
    public void onTest7(View v) {
        startActivity(new Intent(this, ThreadPoolActivity7.class));
    }


}