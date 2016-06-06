package com.example.administrator.bar;

import android.support.v7.app.ActionBar;

/**
 * Created by Administrator on 2016/5/14.
 */
public class DetailActivity extends BaseActivity {

    protected void initView(){
        setContentView(R.layout.activity_detail);
    }

    protected void initActionBar(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
