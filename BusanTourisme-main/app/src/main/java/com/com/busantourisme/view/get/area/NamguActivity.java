package com.com.busantourisme.view.get.area;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.busantourisme.InitMethod;
import com.com.busantourisme.R;
import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.controller.TourController;
import com.com.busantourisme.helper.BottomHelper;
import com.com.busantourisme.model.tour.Tour;
import com.com.busantourisme.view.bar.AppBarActivity;
import com.com.busantourisme.view.get.adapter.MainAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NamguActivity extends AppBarActivity implements InitMethod {



    //다른 클래스에 메모리 전달
    private Context mContext = NamguActivity.this;
    private RecyclerView rvTorus;
    private static final int ACTIVITY_NUM = 1;
    private TourController tourController;
    private List<Tour> tours = new ArrayList<>();
    //방향 설정
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter mainAdapter;
    private String area = "남구";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busan_area);

        onAppBarSettings(true);
        setupBottomNavigationView();


        
        init();
        initAdapter();
        initData();

    }

    @Override
    public void init() {
        tourController = new TourController();
        rvTorus = findViewById(R.id.rvTours);
    }

    @Override
    public void initLr() {

    }

    @Override
    public void initData() {

        tourController.findArea(area).enqueue(new Callback<CMRespDto<List<Tour>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Tour>>> call, Response<CMRespDto<List<Tour>>> response) {
                CMRespDto<List<Tour>> cm = response.body();
                mainAdapter.addItems(cm.getData());
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Tour>>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void initSetting() {

    }


    @Override
    public void initAdapter() {
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvTorus.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(mContext,tours);
        rvTorus.setAdapter(mainAdapter);
    }



    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomHelper.enableBottomNavi(mContext,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}