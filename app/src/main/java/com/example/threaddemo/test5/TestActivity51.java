package com.example.threaddemo.test5;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

/**
 * ThreadLocal
 */
public class TestActivity51 extends AppCompatActivity {

    private static ThreadLocal<String> localVar = new ThreadLocal<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test51);
    }

    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }

    /**
     * test1
     */
    public void onTest1(View v) {

        new Thread(new Runnable() {
            public void run() {
                TestActivity51.localVar.set("local_A");
                print("A");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());

            }
        },"A").start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                TestActivity51.localVar.set("local_B");
                print("B");
                System.out.println("after remove : " + localVar.get());

            }
        },"B").start();
    }



}

