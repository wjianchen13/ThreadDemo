package com.example.threaddemo.thread.test3;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;
import com.example.threaddemo.thread.test2.AbstractStorage;
import com.example.threaddemo.thread.test2.Consumer;
import com.example.threaddemo.thread.test2.Producer;
import com.example.threaddemo.thread.test2.Storage1;

import java.util.concurrent.CountDownLatch;

/**
 * CountdownLatch
 * 参考文档：
 * https://xie.infoq.cn/article/af89076b2bdce72b323dfbe18
 */
public class TestThreadActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread3);
    }

    /**
     * 测试
     *
     */
    public void onTest1(View v) {
        CountDownLatch latch = new CountDownLatch(2);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 is waiting");
                try {
                    latch.await();
                    System.out.println("thread1 go");
                } catch (InterruptedException e) {
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 count down");
                latch.countDown();
                System.out.println("thread2 go");

            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread3 count down");
                latch.countDown();
                System.out.println("thread3 go");

            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }


}