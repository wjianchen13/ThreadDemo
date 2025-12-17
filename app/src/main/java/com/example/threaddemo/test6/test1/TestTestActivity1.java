package com.example.threaddemo.test6.test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;
import com.example.threaddemo.thread.test1.TestThreadActivity1;

/**
 * 测试多个线程实例化不同对象同步效果
 *
 */
public class TestTestActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_test1);
    }

    /**
     * 测试多个线程实例化不同对象同步效果
     */
    public void onTest1(View v) {
        // 获取 Test1 实例
        Test1 test1 = TestManager.getCore(Test1.class);

        // 获取 Test2 实例
        Test2 test2 = TestManager.getCore(Test2.class);
    }

    /**
     *
     */
    public void onTest2(View v) {
        TestManager.getCore(Test1.class).showToast();
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