package com.example.threaddemo.test4;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.threaddemo.R;
import com.example.threaddemo.Utils;

/**
 * 线程池使用
 * 文档：
 * HandlerThread原理、使用实例、源码详细解析
 * https://blog.csdn.net/haovin/article/details/89787721
 *
 */
public class TestActivity41 extends AppCompatActivity {

    private TextView mTxtShowTest;

    private Handler mHandlerInHandlerThread;

    //1、创建HandlerThread对象，即创建了一个新的线程，参数为线程名，仅仅是标记线程用的
    private HandlerThread mHandlerThread = new HandlerThread("mHandlerThread");

    private int count;

    //匿名内部类，用于子线程向主线程通信用
    private Handler mhandlerInnerClass =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Utils.log("mhandlerInnerClass  isMainThread: " + isMainThread() );
            switch (msg.what){
                case 1:
                    //执行的UI操作
                    mTxtShowTest.setText("点击了mBtnInnerClass");
                    break;
                case 2:
                    //执行的UI操作
                    int count = (int)msg.obj;
                    mTxtShowTest.setText("来自于mHandlerInHandlerThread的请求更新: " + count);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test41);
        mTxtShowTest = (TextView) findViewById(R.id.tv_test);

        //2、开启线程，第一步创建了一个新的线程，此处开启线程。
        mHandlerThread.start();

        //3、创建Handler，并重写handleMessage()方法
        //new Handler(mHandlerThread.getLooper())，即把该Handler绑定
        //到了mHandlerThread线程的Looper，进而绑定到了线程mHandlerThread
        mHandlerInHandlerThread = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                //已经是在子线程mHandlerThread中运行了
                //可以进行一些耗时等操作
                try{
                    Thread.sleep(3000);  //延时操作
                }catch (Exception e){
                    e.getMessage();
                }

                //子线程向主线程通信
                Message msg1 = Message.obtain();
                msg1.what = 2;
                msg1.obj = count++;
                if(mhandlerInnerClass != null)
                    mhandlerInnerClass.sendMessage(msg1);
                Utils.log("mHandlerInHandlerThread count: " + count + " isMainThread: " + isMainThread() );

                super.handleMessage(msg);
            }
        };
    }


    /**
     * 线程发送消息
     */
    public void onTest1(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1;
                mhandlerInnerClass.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 主线程向子线程发送信息，通信
     * @param v
     */
    public void onTest2(View v) {

        //4、创建消息并发送消息
        //主线程中
        Message msg = Message.obtain();
        //主线程向子线程发送信息，通信
        mHandlerInHandlerThread.sendMessage(msg);
    }

    /**
     * 结束HanderThread
     */
    public void onTest3(View v) {
        //5、结束线程。之前开启线程，当工作结束不再使用该线程时，应该结束该线程
        //即停止了线程的消息循环

        if(mHandlerThread != null) {
            mHandlerThread.quit();
            mHandlerThread = null;
        }
        if(mHandlerInHandlerThread != null) {
            mHandlerInHandlerThread.removeCallbacksAndMessages(null);
            mHandlerInHandlerThread = null;
        }
        if(mhandlerInnerClass != null) {
            mhandlerInnerClass.removeCallbacksAndMessages(null);
            mhandlerInnerClass = null;
        }
    }

    private boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

}