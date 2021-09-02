package com.com.busantourisme.view.get.festival;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.busantourisme.InitMethod;
import com.com.busantourisme.R;
import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.controller.FestivalController;
import com.com.busantourisme.helper.BottomHelper;
import com.com.busantourisme.model.festival.Festival;
import com.com.busantourisme.view.bar.AppBarActivity;
import com.com.busantourisme.view.get.adapter.FestivalAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FestivalActivity extends AppBarActivity implements InitMethod {

    private static final String TAG = "FestivalActivity";
    private FestivalActivity mContext = this;
    private RecyclerView rvEvent;
    private FestivalController festivalController;

    private RecyclerView.LayoutManager rvLayoutManager;
    private FestivalAdapter festivalAdapter;

    private List<Festival> festivals =  new ArrayList<>();
    private static final int ACTIVITY_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busan_event);

        onAppBarSettings(true);
        setupBottomNavigationView();

        init();
        initAdapter();
        initData();
    }


    @Override
    public void init() {
        festivalController = new FestivalController();
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
        rvLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rvEvent.setLayoutManager(rvLayoutManager);
        festivalAdapter = new FestivalAdapter(mContext,festivals);
        rvEvent.setAdapter(festivalAdapter);
    }


    @Override
    public void initData() {

        festivalController.findAll().enqueue(new Callback<CMRespDto<List<Festival>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Festival>>> call, Response<CMRespDto<List<Festival>>> response) {
                CMRespDto<List<Festival>> cm = response.body();
                festivalAdapter.addItems(cm.getData());
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Festival>>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomHelper.enableBottomNavi(mContext,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}