package com.example.threaddemo.thread.test5;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;

/**
 * 线程池解析消息测试
 */
public class TestThreadActivity5 extends AppCompatActivity {

    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread5);

        tvMessage = findViewById(R.id.tv_message);

        // 接收到消息数据
        String jsonData = "{\"id\":1, \"content\":\"Hello\", \"sender\":\"张三\"}";

        // 解析消息
        MessageThreadPool.getInstance().parseMessage(
                // 后台解析
                new MessageThreadPool.Parser<ChatMessage>() {
                    @Override
                    public ChatMessage parse() throws Exception {
                        // 这里执行耗时的解析操作
                        return new ChatMessage();
                    }
                },
                // 主线程回调
                new MessageThreadPool.Callback<ChatMessage>() {
                    @Override
                    public void onSuccess(ChatMessage message) {
                        // 更新UI
                        tvMessage.setText(message.getContent());
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Activity销毁时不需要关闭线程池
        // 只在应用退出时关闭
    }
}