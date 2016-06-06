package com.example.administrator.bar.fragment;

import android.view.View;

import com.example.administrator.bar.view.LoadingPage;

/**
 * Created by Administrator on 2016/5/14.
 */
public class GameFragment extends BaseFragment {

    @Override
    public View createSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }
}
