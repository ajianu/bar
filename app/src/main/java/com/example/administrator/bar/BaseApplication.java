package com.example.administrator.bar;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by Administrator on 2016/5/15.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    private static int mainTid;
    private static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        mainTid = Process.myTid();
        handler=new Handler();
    }

    public static Context getApplication() {
        return application;
    }

    public static int getMainTid(){
        return mainTid;
    }

    public static Handler getHandler(){
        return handler;
    }
}
