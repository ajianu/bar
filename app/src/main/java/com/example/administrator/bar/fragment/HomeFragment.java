package com.example.administrator.bar.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.bar.protocol.HomeProtocol;
import com.example.administrator.bar.view.LoadingPage;

/**
 * Created by Administrator on 2016/5/14.
 */
public class HomeFragment extends BaseFragment {
    //当Fragment挂载的activity创建的时候调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    public View createSuccessView() {
        TextView tv=new TextView(getActivity());
        tv.setText("加载成功了。。。");
        tv.setTextSize(30);
        return tv;
    }
    public LoadingPage.LoadResult load() {
        HomeProtocol protocol=new HomeProtocol();
        protocol.load(0);
        return LoadingPage.LoadResult.success;
    }

}
