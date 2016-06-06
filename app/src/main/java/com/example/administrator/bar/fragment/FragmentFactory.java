package com.example.administrator.bar.fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/15.
 */
public class FragmentFactory {
    private static Map<Integer,BaseFragment> mFragments=new HashMap<Integer,BaseFragment>();
    public static BaseFragment creatFragment(int position){
        BaseFragment fragment=null;
        fragment=mFragments.get(position);//在集合中取回Fragment
        if (fragment==null) {
            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new AppFragment();
            } else if (position == 2) {
                fragment = new GameFragment();
            } else if (position == 3) {
                fragment = new SubjectFragment();
            } else if (position == 4) {
                fragment = new CategoryFragment();
            } else if (position == 5) {
                fragment = new TopFragment();
            }
            if (fragment != null) {
                mFragments.put(position, fragment);//把创建好的Fragment存放到集合中缓存起来
            }
        }
        return fragment;
    }
}
