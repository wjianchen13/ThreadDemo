package com.example.threaddemo.test6.test1;

public class Test2 {

    private static volatile Test2 INSTANCE = null;

    public Test2() {

    }

//    public static Test2 getInstance() {
//        if (INSTANCE == null) {
//            synchronized(Test2.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Test2();
//                }
//            }
//        }
//        return INSTANCE;
//    }
    
}
