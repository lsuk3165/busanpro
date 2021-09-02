package com.com.busantourisme.view.get.Tour;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.busantourisme.InitMethod;
import com.com.busantourisme.R;
import com.com.busantourisme.controller.FestivalController;
import com.com.busantourisme.helper.BottomHelper;
import com.com.busantourisme.view.bar.AppBarActivity;
import com.com.busantourisme.view.get.adapter.CommentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;


//Adapter와 RecycleView 연결
public class TourCommentActivity extends AppBarActivity implements InitMethod {

    private static final String TAG = "CommentActivity";
    private TourCommentActivity mContext = TourCommentActivity.this;

    private RecyclerView.LayoutManager rvLayoutManager;
    private RecyclerView rvEvent;
    private CommentAdapter commentAdapter;
    private FestivalController postController;
    private static final int ACTIVITY_NUM=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);



        init();
        initLr();
        initData();
        initAdapter();
        initSetting();
    }

    @Override
    public void init() {
        //PostController 추가해야함
        //리사이클뷰 그림림
       rvEvent = findViewById(R.id.rvEvent);

    }

    @Override
    public void initLr() {

    }

    @Override
    public void initSetting() {
        onAppBarSettings(true);
        setupBottomNavigationView();
    }

    @Override
    public void initAdapter() {
        rvLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL, false);
        rvEvent.setLayoutManager(rvLayoutManager);
        commentAdapter = new CommentAdapter(mContext);
        rvEvent.setAdapter(commentAdapter);

    }

    @Override
    public void initData() {

    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomHelper.enableBottomNavi(mContext,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}