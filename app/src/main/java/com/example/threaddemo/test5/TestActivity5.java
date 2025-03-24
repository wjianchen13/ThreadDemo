package com.example.threaddemo.test5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;
import com.example.threaddemo.test4.TestActivity41;
import com.example.threaddemo.test4.TestActivity42;

/**
 * ThreadLocal
 */
public class TestActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);
    }

    /**
     * test1
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, TestActivity51.class));
    }

    /**
     * 模拟项目
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, TestActivity42.class));
    }


}