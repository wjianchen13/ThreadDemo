package com.example.threaddemo.test6.test1;

import android.widget.Toast;

public class Test1 {

    private static volatile Test1 INSTANCE = null;

    public Test1() {

    }

//    public static Test1 getInstance() {
//        if (INSTANCE == null) {
//            synchronized(Test1.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Test1();
//                }
//            }
//        }
//        return INSTANCE;
//    }

    public void showToast() {
        System.out.println("==================> showToast");
    }

}
