package com.example.threaddemo.test2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;
import com.example.threaddemo.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池使用
 * 文档：
 * https://blog.csdn.net/ZHANGLIZENG/article/details/127833510?spm=1001.2101.3001.6650.8&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogOpenSearchComplete%7ERate-8-127833510-blog-135510149.235%5Ev43%5Epc_blog_bottom_relevance_base2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogOpenSearchComplete%7ERate-8-127833510-blog-135510149.235%5Ev43%5Epc_blog_bottom_relevance_base2&utm_relevant_index=13
 *
 *
 */
public class TestActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
    }

    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * CallerRunsPolicy 拒绝策略例子
     * 当任务添加到线程池中被拒绝时，判断线程池是否还在运行，直接在主线程中运行此任务，即在调用execute或者submit的方法中执行，不再使用线程池来处理此任务。
     * @param v
     */
    public void onTest1(View v) {
        threadPoolExecutor = new ThreadPoolExecutor(2, 2, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new ThreadPoolExecutor.CallerRunsPolicy());

        //此时有6个任务，最大线程+队列能处理4个，主线程需要处理6-4=2个
        for(int i = 0; i < 1; i ++) {
            Runnable run = new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                        Utils.log("执行当前任务的线程："+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPoolExecutor.execute(run);
            Utils.log("pool size: " + threadPoolExecutor.getPoolSize());
        }
    }

    /**
     * 获取线程池数量
     * 把上面的线程池数量改成1，就可以测试线程池中的数量
     * 所以应该是每次添加任务，检测当前线程池中的线程是否小于corePoolSize，小于则新建线程，而不是第一次任务来了就直接创建corePoolSize数量的线程
     * @param v
     */
    public void onTest2(View v) {
        if(threadPoolExecutor != null) {
            Utils.log("pool size: " + threadPoolExecutor.getPoolSize());
        } else {
            Utils.log("threadPoolExecutor != null");
        }
    }

    /**
     * newFixedThreadPool 创建线程池
     * 首先会创建4个线程，剩下的放到任务队列，因为任务队列没有满，所以maximumPoolSize这个参数不起作用，然后公用开始创建的4个线程执行剩下的任务。
     */
    public void onTest3(View v) {
        // 创建定长线程池
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            //创建任务
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(3);
                        System.out.println("当前执行的线程为:"+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            //任务添加到线程池
            newFixedThreadPool.execute(runnable);
        }
    }

    /**
     * newSingleThreadExecutor 创建线程池
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