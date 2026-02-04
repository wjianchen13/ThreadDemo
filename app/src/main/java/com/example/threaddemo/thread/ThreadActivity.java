package com.example.threaddemo.thread;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;
import com.example.threaddemo.Utils;
import com.example.threaddemo.thread.test1.TestThreadActivity1;
import com.example.threaddemo.thread.test2.TestThreadActivity2;
import com.example.threaddemo.thread.test3.TestThreadActivity3;
import com.example.threaddemo.thread.test4.TestThreadActivity4;
import com.example.threaddemo.thread.test5.TestThreadActivity5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程
 * CyclicBarrier和Semaphore：
 *
 */
public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    /**
     * 线程同步测试
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, TestThreadActivity1.class));
    }

    /**
     * 生产者消费者模型
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, TestThreadActivity2.class));
    }

    /**
     * CountdownLatch
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, TestThreadActivity3.class));
    }

    /**
     * CyclicBarrier
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, TestThreadActivity4.class));
    }

    /**
     * 线程池解析消息测试
     */
    public void onTest5(View v) {
        startActivity(new Intent(this, TestThreadActivity5.class));
    }

    /**
     *
     */
    public void onTest6(View v) {

    }

}