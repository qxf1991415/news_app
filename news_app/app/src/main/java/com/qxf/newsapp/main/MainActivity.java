package com.qxf.newsapp.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.qxf.newsapp.R;
import com.qxf.newsapp.base.BaseActivity;
import com.qxf.newsapp.base.SettingActivity;
import com.qxf.newsapp.find.FindFrgment;
import com.qxf.newsapp.frends.FrendsFrgment;
import com.qxf.newsapp.home.HomeFrgment;
import com.qxf.newsapp.news.NewsFrgment;
import com.qxf.newsapp.talk.TalkFrgment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private static final int FRAGMENT_FLAG_A = 0X01;
    private static final int FRAGMENT_FLAG_B = 0X02;
    private static final int FRAGMENT_FLAG_C = 0X03;
    private static final int FRAGMENT_FLAG_D = 0X04;
    private static final int FRAGMENT_FLAG_E = 0X05;

    @BindView(R.id.index_tab_a)
    RadioButton indexTabA;
    @BindView(R.id.index_tab_b)
    RadioButton indexTabB;
    @BindView(R.id.index_tab_c)
    RadioButton indexTabC;
    @BindView(R.id.index_tab_d)
    RadioButton indexTabD;
    @BindView(R.id.index_tab_e)
    RadioButton indexTabE;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private NewsFrgment fragmentNews;
    private TalkFrgment fragmentTalk;
    private FrendsFrgment fragmentFrends;
    private FindFrgment fragmentFind;
    private HomeFrgment fragmentHome;

    private Context context;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        setToolBarMenuOnclick(new mainToolBarMenuClick());
        ButterKnife.bind(this);
        context = MainActivity.this;
        initView1();
        //创建返回键，并实现打开关/闭监听
        drawerToggleListen();
        //mNavigationView点击监听
        navigationViewListen();
    }

    private void navigationViewListen() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_news:
                        changeTab(FRAGMENT_FLAG_A);
                        setRadioChecked(R.id.nav_news);
                        break;
                    case R.id.nav_talk:
                        changeTab(FRAGMENT_FLAG_B);
                        setRadioChecked(R.id.nav_talk);
                        break;
                    case R.id.nav_frends:
                        changeTab(FRAGMENT_FLAG_C);
                        setRadioChecked(R.id.nav_frends);
                        break;
                    case R.id.nav_find:
                        changeTab(FRAGMENT_FLAG_D);
                        setRadioChecked(R.id.nav_find);
                        break;
                    case R.id.nav_home:
                        changeTab(FRAGMENT_FLAG_E);
                        setRadioChecked(R.id.nav_home);
                        break;
                    case R.id.nav_set:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setRadioChecked(@IdRes int ids) {
        indexTabA.setChecked(ids == R.id.nav_news);
        indexTabB.setChecked(ids == R.id.nav_talk);
        indexTabC.setChecked(ids == R.id.nav_frends);
        indexTabD.setChecked(ids == R.id.nav_find);
        indexTabE.setChecked(ids == R.id.nav_home);
    }

    private void drawerToggleListen() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, commonTitleTb, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "打开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "关闭了", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initView1() {
        indexTabA.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_A));
        indexTabB.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_B));
        indexTabC.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_C));
        indexTabD.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_D));
        indexTabE.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_E));
        indexTabA.setChecked(true);
        mNavigationView.setCheckedItem(R.id.nav_news);
    }

    class mainToolBarMenuClick implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_refresh:
                    Toast.makeText(context, "refresh", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_message:
                    Toast.makeText(context, "message", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 定义RadioButton的选中改变监听
     */
    class OnNaviCheckChangeListener implements CompoundButton.OnCheckedChangeListener {

        private int posttion;

        public OnNaviCheckChangeListener(int posttion) {
            this.posttion = posttion;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                changeTab(posttion);
            }
        }
    }

    /**
     * 切换tab
     */
    private void changeTab(int posttion) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (posttion) {
            case FRAGMENT_FLAG_A:
                mNavigationView.setCheckedItem(R.id.nav_news);
                changeTabToFragmentA(transaction);
                break;
            case FRAGMENT_FLAG_B:
                mNavigationView.setCheckedItem(R.id.nav_talk);
                changeTabToFragmentB(transaction);
                break;
            case FRAGMENT_FLAG_C:
                mNavigationView.setCheckedItem(R.id.nav_frends);
                changeTabToFragmentC(transaction);
                break;
            case FRAGMENT_FLAG_D:
                mNavigationView.setCheckedItem(R.id.nav_find);
                changeTabToFragmentD(transaction);
                break;
            case FRAGMENT_FLAG_E:
                mNavigationView.setCheckedItem(R.id.nav_home);
                changeTabToFragmentE(transaction);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 跳转到fragmentA
     */
    private void changeTabToFragmentA(FragmentTransaction transaction) {
        setTitle(R.string.tab_a);
        if (fragmentNews == null) {
            fragmentNews = new NewsFrgment();
            transaction.add(R.id.index_content_fl, fragmentNews);
        } else {
            transaction.show(fragmentNews);
        }
    }

    /**
     * 跳转到fragmentB
     */
    private void changeTabToFragmentB(FragmentTransaction transaction) {
        setTitle(R.string.tab_b);
        if (fragmentTalk == null) {
            fragmentTalk = new TalkFrgment();
            transaction.add(R.id.index_content_fl, fragmentTalk);
        } else {
            transaction.show(fragmentTalk);
        }

    }

    /**
     * 跳转到fragmentC
     */
    private void changeTabToFragmentC(FragmentTransaction transaction) {
        setTitle(R.string.tab_c);
        if (fragmentFrends == null) {
            fragmentFrends = new FrendsFrgment();
            transaction.add(R.id.index_content_fl, fragmentFrends);
        } else {
            transaction.show(fragmentFrends);
        }
    }

    /**
     * 跳转到fragmentD
     */
    private void changeTabToFragmentD(FragmentTransaction transaction) {
        setTitle(R.string.tab_d);
        if (fragmentFind == null) {
            fragmentFind = new FindFrgment();
            transaction.add(R.id.index_content_fl, fragmentFind);
        } else {
            transaction.show(fragmentFind);
        }
    }

    private void changeTabToFragmentE(FragmentTransaction transaction) {
        setTitle(R.string.tab_e);
        if (fragmentHome == null) {
            fragmentHome = new HomeFrgment();
            transaction.add(R.id.index_content_fl, fragmentHome);
        } else {
            transaction.show(fragmentHome);
        }
    }

    /**
     * 先隐藏掉所有的fragment
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (fragmentNews != null && !fragmentNews.isHidden()) {
            transaction.hide(fragmentNews);
        }
        if (fragmentTalk != null && !fragmentTalk.isHidden()) {
            transaction.hide(fragmentTalk);
        }
        if (fragmentFrends != null && !fragmentFrends.isHidden()) {
            transaction.hide(fragmentFrends);
        }
        if (fragmentFind != null && !fragmentFind.isHidden()) {
            transaction.hide(fragmentFind);
        }
        if (fragmentHome != null && !fragmentHome.isHidden()) {
            transaction.hide(fragmentHome);
        }
    }
}
