package com.example.love.bootbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private LinearLayout bottom_nav_content;//内容区域
    private BottomNavigationBar bottom_navigation_bar_container;//底部导航栏
    private BottomNavigationItem msgItem;
    private BottomNavigationItem taskItem;
    private BottomNavigationItem noticeItem;
    private BadgeItem badgeItem;
    private MsgFragment msgFrag;
    private TaskFragment taskFrag;
    private NoticeFragment noticeFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBottomNavBar();
    }


    private void initView() {
        bottom_nav_content = (LinearLayout) findViewById(R.id.bottom_nav_content);
        bottom_navigation_bar_container = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar_container);

    }

    /*初始化底部导航栏*/
    private void initBottomNavBar() {

        bottom_navigation_bar_container.setAutoHideEnabled(true);//自动隐藏

        //BottomNavigationBar.MODE_SHIFTING;
        //BottomNavigationBar.MODE_FIXED;
        //BottomNavigationBar.MODE_DEFAULT;
        bottom_navigation_bar_container.setMode(BottomNavigationBar.MODE_FIXED);

        // BottomNavigationBar.BACKGROUND_STYLE_DEFAULT;
        // BottomNavigationBar.BACKGROUND_STYLE_RIPPLE
        // BottomNavigationBar.BACKGROUND_STYLE_STATIC
        bottom_navigation_bar_container.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottom_navigation_bar_container.setBarBackgroundColor(R.color.white);//背景颜色
        bottom_navigation_bar_container.setInActiveColor(R.color.nav_gray);//未选中时的颜色
        bottom_navigation_bar_container.setActiveColor(R.color.colorPrimaryDark);//选中时的颜色
        badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("1").setHideOnSelect(true);//角标

        msgItem = new BottomNavigationItem(R.drawable.tv, "消息");
        msgItem.setBadgeItem(badgeItem);
        taskItem = new BottomNavigationItem(R.drawable.tag, "任务");
        noticeItem = new BottomNavigationItem(R.drawable.data, "公告");


        bottom_navigation_bar_container.addItem(msgItem).addItem(taskItem).addItem(noticeItem);
        bottom_navigation_bar_container.initialise();
        bottom_navigation_bar_container.setTabSelectedListener(this);
        setDefaultFrag();//显示默认的Frag

    }

    /*设置默认Fragment*/
    private void setDefaultFrag() {


        if (msgFrag == null) {

            msgFrag = new MsgFragment();
        }


        addFrag(msgFrag);
        /*默认显示msgFrag*/
        getSupportFragmentManager().beginTransaction().show(msgFrag).commit();
        getSupportActionBar().setTitle("消息");


    }

    /*添加Frag*/
    private void addFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (frag != null && !frag.isAdded()) {

            ft.add(R.id.bottom_nav_content, frag);

        }

        ft.commit();


    }

    /*隐藏所有fragment*/
    private void hideAllFrag() {
        hideFrag(msgFrag);
        hideFrag(taskFrag);

        hideFrag(noticeFrag);
    }

    /*隐藏frag*/
    private void hideFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (frag != null && frag.isAdded()) {
            ft.hide(frag);
        }
        ft.commit();
    }

    /*底部NaV监听*/
    @Override
    public void onTabSelected(int position) {
        hideAllFrag();//先隐藏所有frag
        switch (position) {
            case 0:
                if (msgFrag == null) {
                    msgFrag = new MsgFragment();
                }
                addFrag(msgFrag);
                getSupportFragmentManager().beginTransaction().show(msgFrag).commit();
                getSupportActionBar().setTitle("消息");

                break;
            case 1:
                if (taskFrag == null) {

                    taskFrag = new TaskFragment();
                }

                addFrag(taskFrag);
                getSupportFragmentManager().beginTransaction().show(taskFrag).commit();
                getSupportActionBar().setTitle("任务");

                break;
            case 2:
               /*公告Frag*/
                if (noticeFrag == null) {
                    noticeFrag = new NoticeFragment();

                }
                addFrag(noticeFrag);
                getSupportFragmentManager().beginTransaction().show(noticeFrag).commit();
                getSupportActionBar().setTitle("公告");
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {


    }

    @Override
    public void onTabReselected(int position) {

    }
}
