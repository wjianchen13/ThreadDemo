package com.example.threaddemo.test6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;
import com.example.threaddemo.test6.test1.TestTestActivity1;
import com.example.threaddemo.thread.test1.TestThreadActivity1;
import com.example.threaddemo.thread.test2.TestThreadActivity2;
import com.example.threaddemo.thread.test3.TestThreadActivity3;
import com.example.threaddemo.thread.test4.TestThreadActivity4;

/**
 * 测试
 *
 */
public class TestActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test6);
    }

    /**
     * 测试多个线程实例化不同对象同步效果
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, TestTestActivity1.class));
    }

    /**
     *
     */
    public void onTest2(View v) {

    }

    /**
     *
     */
    public void onTest3(View v) {

    }

    /**
     *
     */
    public void onTest4(View v) {

    }

    /**
     *
     */
    public void onTest5(View v) {

    }

    /**
     *
     */
    public void onTest6(View v) {

    }

}