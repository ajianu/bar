package com.example.administrator.bar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/15.
 */
public class BaseActivity extends ActionBarActivity {
    //管理运行的所有的Activity
    static List<BaseActivity> mActivity=new LinkedList<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        synchronized (mActivity){
            mActivity.add(this);
        }

        init();
        initView();
        initActionBar();
    }

    protected void onDestroy(){
        super.onDestroy();
        synchronized (mActivity) {
            mActivity.remove(this);
        }
    }

    public void killall(){
        //复制一份Activities集合
        List<BaseActivity> copy;
        synchronized (mActivity) {
            copy = new LinkedList<BaseActivity>(mActivity);
        }
        for (BaseActivity activity:copy){
            activity.finish();
        }
        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected void initView() {

    }

    protected void initActionBar() {

    }

    protected void init() {

    }
}
