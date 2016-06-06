package com.example.administrator.bar.fragment;

import android.view.View;

import com.example.administrator.bar.view.LoadingPage.LoadResult;

/**
 * Created by Administrator on 2016/5/14.
 */
public class AppFragment extends BaseFragment {

    @Override
    public View createSuccessView() {
        return null;
    }

    @Override
    public LoadResult load() {
        return LoadResult.error;
    }
}
