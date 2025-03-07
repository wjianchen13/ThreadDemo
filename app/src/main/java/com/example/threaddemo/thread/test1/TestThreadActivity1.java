package com.example.threaddemo.thread.test1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步方式
 *
 */
public class TestThreadActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread1);
    }

    /**
     * synchronized修饰普通方法
     * 文档：
     * Android 多线程——线程安全/线程同步
     * https://blog.csdn.net/liuning1985622/article/details/138394022
     *
     */
    public void onTest1(View v) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (Thread.currentThread().getName().equalsIgnoreCase("thread1")) {
                    funA();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread2")) {
                    funB();
                } else {
                    funC();
                }
            }

            private synchronized void funA() {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("[" + Thread.currentThread().getName() + "] funA " + i);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            private synchronized void funB() {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("[" + Thread.currentThread().getName() + "] funB " + i);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            private synchronized void funC() {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("[" + Thread.currentThread().getName() + "] funC " + i);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable, "thread1").start();
        new Thread(runnable, "thread2").start();
        new Thread(runnable, "thread3").start();
    }

    /**
     * synchronized修饰代码块
     */
    public void onTest2(View v) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (Thread.currentThread().getName().equalsIgnoreCase("thread1")) {
                    funA();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread2")) {
                    funB();
                } else {
                    funC();
                }
            }

            private void funA() {
                synchronized (this) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funA " + i);
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            private void funB() {
                synchronized (this) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funB " + i);
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            private void funC() {
                synchronized (this) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funC " + i);
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        new Thread(runnable, "thread1").start();
        new Thread(runnable, "thread2").start();
        new Thread(runnable, "thread3").start();
    }

    /**
     * synchronized修饰对象
     */
    public void onTest3(View v) {
        Runnable runnable = new Runnable() {
            List<Integer> list = new ArrayList();
            List<Integer> list1 = new ArrayList();

            @Override
            public void run() {
                if (Thread.currentThread().getName().equalsIgnoreCase("thread1")) {
                    funA();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread2")) {
                    funB();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread3")) {
                    funC();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread4")) {
                    funD();
                }
            }

            private void funA() {
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                synchronized (list) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funA " + list.get(i));
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            }

            private void funB() {
                synchronized (list) {
                    list.clear();
                    System.out.println("[" + Thread.currentThread().getName() + "] funB " + list.size());
                }
            }

            private void funC() {
                list1.add(1);
                list1.add(2);
                list1.add(3);
                list1.add(4);
                list1.add(5);
                synchronized (list1) {
                    int size = list1.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funC " + list1.get(i));
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            }

            private void funD() {
                synchronized (list1) {
                    list1.clear();
                    System.out.println("[" + Thread.currentThread().getName() + "] funD " + list1.size());
                }
            }
        };
        new Thread(runnable, "thread1").start();

        // thread2有意保证晚一点再运行
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(runnable, "thread2").start();

        new Thread(runnable, "thread3").start();

        // thread2有意保证晚一点再运行
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(runnable, "thread4").start();
    }
    /**
     * synchronized修饰静态方法
     */
    public void onTest4(View v) {
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                if (Thread.currentThread().getName().equalsIgnoreCase("thread1")) {
                    funA();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread2")) {
                    funB();
                }
            }

            private synchronized static void funA() {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("[" + Thread.currentThread().getName() + "] funA " + i);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            private synchronized static void funB() {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("[" + Thread.currentThread().getName() + "] funB " + i);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        new Thread(new MyRunnable(), "thread1").start();
        new Thread(new MyRunnable(), "thread2").start();
    }

    /**
     * synchronized修饰静态代码，锁定一个类
     * @param v
     */
    public void onTest5(View v) {
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                if (Thread.currentThread().getName().equalsIgnoreCase("thread1")) {
                    funA();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread2")) {
                    funB();
                }
            }

            private static void funA() {
                synchronized (MyRunnable.class) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funA " + i);
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            private static void funB() {
                synchronized (MyRunnable.class) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funB " + i);
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        new Thread(new MyRunnable(), "thread1").start();
        new Thread(new MyRunnable(), "thread2").start();
    }

    /**
     * 可重入锁ReentrantLock
     * @param v
     */
    public void onTest6(View v) {
        Lock lock = new ReentrantLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (Thread.currentThread().getName().equalsIgnoreCase("thread1")) {
                    funA();
                } else if (Thread.currentThread().getName().equalsIgnoreCase("thread2")) {
                    funB();
                }
            }

            private void funA() {
                try {
                    lock.lock();
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funA " + i);
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }

            private void funB() {
                try {
                    lock.lock();
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] funB " + i);
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        };
        new Thread(runnable, "thread1").start();
        new Thread(runnable, "thread2").start();

    }

}