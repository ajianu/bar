package com.example.administrator.bar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.bar.tools.ViewUtils;
import com.example.administrator.bar.view.LoadingPage;

/**
 * Created by Administrator on 2016/5/15.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (loadingPage==null) {
            loadingPage = new LoadingPage(getActivity()) {
                public View createSuccessView(){
                return BaseFragment.this.createSuccessView();
                }

                protected LoadResult load(){
                    return BaseFragment.this.load();
                }
            };
        }else{
            ViewUtils.removeParent(loadingPage);//移除framelayout之前的爹
        }
        //show();//根据服务器的数据切换状态
        return loadingPage;
    }


    /*
    * 创建成功的界面
    */
    public abstract View createSuccessView();
    public abstract LoadingPage.LoadResult load();

    public void show() {
        if (loadingPage!=null) {
            loadingPage.show();
        }
    }


}
