package com.demo.store;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by GSJ
 * Date: 2016/10/20
 * Time: 10:46
 */
public class BaseApplication extends Application {

    /** 全局Context*/
    private static BaseApplication mInstance;
    /** 主线程ID */
    private static int mMainThreadId ;
    /** 主线程 */
    private static Thread mMainThread;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;
    /** 主线程Looper */
    private static Looper mMainLooper;
    @Override
    public void onCreate() {
        super.onCreate();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
        long id = Thread.currentThread().getId();
        String name = mMainThread.getName();
        System.out.println("currentThread().getId()====="+id);
        System.out.println("Process.myTid()====="+mMainThreadId);
        System.out.println("mMainThread.getName()====="+name);

    }

    public static BaseApplication getApplication() {
        return mInstance;
    }

    /** 获取主线程ID */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /** 获取主线程 */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /** 获取主线程的handler */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /** 获取主线程的looper */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
}
