package com.nhh.news24h.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;


import com.nhh.news24h.R;
import com.nhh.news24h.adapter.ExplandableListAdapter;
import com.nhh.news24h.adapter.ViewPagerAdapter;
import com.nhh.news24h.fragment.Fragment24H;
import com.nhh.news24h.fragment.FragmentXemVN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trangchu extends AppCompatActivity implements ExpandableListView.OnChildClickListener {
    private Toolbar tbMenu;
    private DrawerLayout dlMenu;
    private ViewPager vpPaper;
    private TabLayout tabPaper;
    private ViewPagerAdapter pagerAdapter;
    private ExpandableListView expandableListView;
    private ExplandableListAdapter mMenuAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private static Context mContext;
    public static int[] icon = {R.drawable.icon24h,R.drawable.xemvnicon};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        initView();
    }


    private void initView() {
        tbMenu = findViewById(R.id.tb_main);
        dlMenu = findViewById(R.id.dl_menu);
        vpPaper = findViewById(R.id.vp_paper);
        tabPaper = findViewById(R.id.tab_title);
        expandableListView = findViewById(R.id.nav_menulist);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        setSupportActionBar(tbMenu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.menu);
        actionBar.setTitle("ĐỌC BÁO ONLINE");

        vpPaper.setAdapter(pagerAdapter);
        tabPaper.setupWithViewPager(vpPaper);

        prepareListData();
        mMenuAdapter = new ExplandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(mMenuAdapter);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("DEBUG", "header item clicked");
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dlMenu.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Context getContext() {
        return mContext;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("24H");
        listDataHeader.add("XemVN");


        List<String> heading1 = new ArrayList<String>();
        heading1.add("Tin tức trong ngày");
        heading1.add("ASIAN CUP 2019");
        heading1.add("An ninh - Hình sự");
        heading1.add("Phim");
        heading1.add("Ca nhạc - MTV");
        heading1.add("Công nghệ thông tin");

        List<String> heading2 = new ArrayList<String>();
        heading2.add("Trang chủ");
        heading2.add("Hot");
        heading2.add("Bình chọn");


        listDataChild.put(listDataHeader.get(0), heading1);
        listDataChild.put(listDataHeader.get(1), heading2);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        switch (groupPosition) {
            case 0:
                vpPaper.setCurrentItem(0);
                switch (childPosition) {
                    case 0:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/tintuctrongngay.rss");
                        break;
                    case 1:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/bongda.rss");
                        break;
                    case 2:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/asiancup2019.rss");
                        break;
                    case 3:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/anninhhinhsu.rss");
                        break;
                    case 4:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/thoitrang.rss");
                        break;
                    case 5:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/taichinhbatdongsan.rss");
                        break;
                    case 6:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/lamdep.rss");
                        break;
                    case 7:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/phim.rss");
                        break;
                    case 8:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/canhacmtv.rss");
                        break;
                    case 9:
                        new Fragment24H.Paper24HData().execute("https://www.24h.com.vn/upload/rss/congnghethongtin.rss");
                        break;


                }
                break;


            case 1:
                vpPaper.setCurrentItem(3);
                switch (childPosition) {
                    case 0:
                        new FragmentXemVN.XemVNData().execute("https://xem.vn/rss/");
                        break;
                    case 1:
                        new FragmentXemVN.XemVNData().execute("https://xem.vn/hot.rss/");
                        break;

                    case 2:
                        new FragmentXemVN.XemVNData().execute("https://xem.vn/vote.rss/");
                        break;
                }
                break;
        }
        dlMenu.closeDrawers();

        return true;
    }
}
