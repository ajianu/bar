package com.example.administrator.bar;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.bar.fragment.BaseFragment;
import com.example.administrator.bar.fragment.FragmentFactory;
import com.example.administrator.bar.tools.UiUtils;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    private String[] tab_names;//标签的名字

    protected void initView(){
        setContentView(R.layout.activity_main);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.dl);
        mViewPager= (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mPagerTabStrip= (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        mPagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment createFragment=FragmentFactory.creatFragment(position);
                createFragment.show();//当切换界面的时候，重新请求服务器
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected  void  initActionBar(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.drawable.
                ic_drawer_am,R.string.open_drawer,
                R.string.close_drawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(getApplicationContext(),"抽屉关闭了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext(),"抽屉打开了",Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(drawerToggle);
        //让开关和activity建立关系
        drawerToggle.syncState();
    }

    protected void init(){
        tab_names = UiUtils.getStringArray(R.array.tab_names);
    }

    private class MainAdapter extends FragmentStatePagerAdapter{

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }
        //每个条目返回的fragment
        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.creatFragment(position);
        }

        @Override
        public int getCount() {
            return tab_names.length;
        }

        //返回每个条目的标题
        public CharSequence getPageTitle(int position){
            return tab_names[position];
        }
    }
       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    //处理actionBar菜单条目的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_search) {
            Toast.makeText(getApplicationContext(),"搜索",Toast.LENGTH_SHORT).show();
        }
        return drawerToggle.onOptionsItemSelected(item)|super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(getApplicationContext(),newText,Toast.LENGTH_SHORT).show();
        return true;
    }
}
