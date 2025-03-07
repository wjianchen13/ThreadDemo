package com.example.threaddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.threaddemo.pool.ThreadPoolActivity;
import com.example.threaddemo.test2.TestActivity2;
import com.example.threaddemo.test4.TestActivity4;
import com.example.threaddemo.thread.ThreadActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 线程
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, ThreadActivity.class));
    }

    /**
     * 线程池
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, ThreadPoolActivity.class));
    }


    /**
     * 线程池2
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, TestActivity2.class));
    }


    /**
     * HandlerThread
     * @param v
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, TestActivity4.class));
    }

}