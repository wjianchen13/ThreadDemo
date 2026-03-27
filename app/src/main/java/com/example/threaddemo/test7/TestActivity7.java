package com.example.threaddemo.test7;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 停止线程
 * 停止线程的方法
 *
 * 方法一：volatile 标志位（上面讲过的）
 * javaprivate volatile boolean isRunning = true;
 *
 * public void stop() {
 *     isRunning = false;
 * }
 *
 * 方法二：interrupt() 中断机制
 * Java 官方推荐的方式：
 * javaThread thread = new Thread(new Runnable() {
 *     @Override
 *     public void run() {
 *         // 检查中断标志
 *         while (!Thread.currentThread().isInterrupted()) {
 *             // 执行任务
 *         }
 *         System.out.println("线程停止了");
 *     }
 * });
 *
 * thread.start();
 *
 * // 停止线程
 * thread.interrupt(); // 设置中断标志为true
 *
 * interrupt() 的特殊之处
 * 线程如果正在 sleep() 或 wait() 时被中断，会抛出 InterruptedException：
 * javanew Thread(new Runnable() {
 *     @Override
 *     public void run() {
 *         try {
 *             while (!Thread.currentThread().isInterrupted()) {
 *                 // 模拟耗时操作
 *                 Thread.sleep(1000);
 *             }
 *         } catch (InterruptedException e) {
 *             // sleep期间被中断，会走到这里
 *             // 需要重新设置中断标志
 *             Thread.currentThread().interrupt();
 *             System.out.println("线程被中断了");
 *         }
 *     }
 * }).start();
 *
 * 方法三：Future + ExecutorService
 * 用线程池管理线程，通过 Future.cancel() 停止：
 * javaExecutorService executor = Executors.newSingleThreadExecutor();
 *
 * Future<?> future = executor.submit(new Runnable() {
 *     @Override
 *     public void run() {
 *         while (!Thread.currentThread().isInterrupted()) {
 *             // 执行任务
 *         }
 *     }
 * });
 *
 * // 停止线程
 * future.cancel(true); // true表示允许中断正在执行的任务
 * executor.shutdown();
 *
 * 方法四：stop()（已废弃，不要用）
 * javathread.stop(); // ❌ 已废弃，非常危险
 * ```
 *
 * 为什么危险：
 * ```
 * stop() 会强制杀死线程
 *         ↓
 * 不管线程当前在做什么，直接停
 *         ↓
 * 可能导致数据写到一半就停了
 *         ↓
 * 数据不一致，资源没有释放
 *         ↓
 * 出现各种奇怪的问题
 *
 * 四种方式对比
 * 方式优点缺点推荐程度volatile标志位简单直观线程sleep时响应慢⭐⭐⭐
 * interrupt()官方推荐，sleep时也能响应需要正确处理异常⭐⭐⭐⭐⭐
 * Future.cancel()适合线程池场景依赖线程池⭐⭐⭐⭐
 * stop()简单粗暴危险，已废弃❌
 *
 * 一句话总结
 *
 * 推荐优先用 interrupt()，这是Java官方推荐的中断机制，能处理线程sleep/wait的情况。volatile标志位适合简单场景，stop() 永远不要用。
 */
public class TestActivity7 extends AppCompatActivity {

    private static final String TAG = "thread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test7);
    }

    // 没有 volatile
    private volatile boolean isRunning = true;

    /**
     * 测试多个线程实例化不同对象同步效果
     */
    public void onTest1(View v) {
        start();
    }

    public void start() {
        isRunning = true;
        // 子线程：一直循环执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (isRunning) {  // 读的是自己本地缓存里的值
//                    System.out.println("子线程正在运行... i=" + i);
//                    Log.d(TAG, "子线程正在运行... i=" + i);
//                    i++;
//                    try {
//                        Thread.sleep(10);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
//                System.out.println("子线程停止了, i=" + i);
                Log.d(TAG, "子线程停止了, i=" + i);
            }
        }).start();

        // 主线程：1秒后停止子线程
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isRunning = false;  // 只改了主线程本地缓存
//        System.out.println("主线程设置 isRunning = false");
        Log.d(TAG, "主线程设置 isRunning = false");
    }

    /**
     * interrupt
     * 线程如果正在 sleep() 或 wait() 时被中断，会抛出 InterruptedException
     */
    public void onTest2(View v) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 检查中断标志
//                while (!Thread.currentThread().isInterrupted()) {
//                    // 执行任务
//                }
//                System.out.println("线程停止了");
//            }
//        });
//
//        thread.start();
//        // 停止线程
//        thread.interrupt(); // 设置中断标志为true

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("开始");
                        // 模拟耗时操作
                        Thread.sleep(1000);
                    }
                    System.out.println("线程停止了");
                } catch (InterruptedException e) {
                    // sleep期间被中断，会走到这里
                    // 需要重新设置中断标志
                    Thread.currentThread().interrupt();
                    System.out.println("线程被中断了");
                }
            }
        });

        thread.start();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 停止线程
        thread.interrupt(); // 设置中断标志为true
    }

    /**
     * Future + ExecutorService
     */
    public void onTest3(View v) {

        // 第一步：创建线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 第二步：提交任务，拿到Future
        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("子线程正在运行... i=" + i);
                    i++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // sleep期间被cancel，走到这里
                        System.out.println("子线程sleep被中断，准备退出");
                        Thread.currentThread().interrupt(); // 重新设置中断标志
                        break; // 退出循环
                    }
                }
                System.out.println("子线程停止了");
            }
        });

        // 第三步：主线程等2秒后停止子线程
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 第四步：取消任务
        boolean cancelled = future.cancel(true); // true表示允许中断正在运行的线程
        System.out.println("取消结果: " + cancelled);

        // 第五步：关闭线程池
        executor.shutdown();
        System.out.println("主线程结束");

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