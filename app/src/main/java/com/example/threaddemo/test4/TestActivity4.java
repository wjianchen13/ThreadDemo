package com.example.threaddemo.test4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

/**
 * HandlerThread
 */
public class TestActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
    }


    /**
     * HandlerThread 测试
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, TestActivity41.class));
    }

    /**
     * 模拟项目
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, TestActivity42.class));
    }


}