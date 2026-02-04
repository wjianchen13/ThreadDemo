package com.example.threaddemo.thread.test5;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 消息解析线程池管理类
 * 用于后台解析数据，完成后切换到主线程更新UI
 */
public class MessageThreadPool {
    private static final String TAG = "MessageThreadPool";

    private static volatile MessageThreadPool instance;

    private final ExecutorService executor;
    private final Handler mainHandler;

    // 线程池大小（CPU核心数）
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private MessageThreadPool() {
        mainHandler = new Handler(Looper.getMainLooper());

        // 创建固定大小的线程池
        executor = Executors.newFixedThreadPool(THREAD_COUNT);

        Log.d(TAG, "线程池初始化完成，线程数: " + THREAD_COUNT);
    }

    public static MessageThreadPool getInstance() {
        if (instance == null) {
            synchronized (MessageThreadPool.class) {
                if (instance == null) {
                    instance = new MessageThreadPool();
                }
            }
        }
        return instance;
    }

    /**
     * 解析消息（后台执行，完成后回调主线程）
     *
     * @param parser 解析任务
     * @param callback 主线程回调
     */
    public <T> void parseMessage(final Parser<T> parser, final Callback<T> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 后台解析数据
                    final T result = parser.parse();

                    // 切换到主线程
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onSuccess(result);
                            }
                        }
                    });

                } catch (final Exception e) {
                    Log.e(TAG, "解析失败", e);

                    // 错误也要切换到主线程
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onError(e);
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            Log.d(TAG, "关闭线程池");
            executor.shutdown();
            try {
                if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        // 清除主线程待执行任务
        if (mainHandler != null) {
            mainHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 解析接口
     */
    public interface Parser<T> {
        T parse() throws Exception;
    }

    /**
     * 回调接口
     */
    public interface Callback<T> {
        void onSuccess(T result);
        void onError(Exception e);
    }
}