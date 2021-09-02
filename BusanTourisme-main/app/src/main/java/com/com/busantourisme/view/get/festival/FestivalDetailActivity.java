package com.com.busantourisme.view.get.festival;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.com.busantourisme.InitMethod;
import com.com.busantourisme.R;
import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.controller.FestivalController;
import com.com.busantourisme.helper.BottomHelper;
import com.com.busantourisme.model.festival.Festival;
import com.com.busantourisme.view.bar.AppBarActivity;
import com.com.busantourisme.view.get.Tour.TourCommentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FestivalDetailActivity extends AppBarActivity implements InitMethod {


    private static final String TAG = "FestivalDetailActivity";
    private FestivalDetailActivity mContext = new FestivalDetailActivity();
    private FestivalController festivalController;
    private ImageView ivImg,ivFav,ivComment;
    private TextView tvTitle,tvCountFav,tvCountCom,tvHomepage;
    private MaterialTextView mtvTraffic,mtvAdd;
    private MaterialButton mbtnCall;
    private static final int ACTIVITY_NUM = 1;
    private int festivalId;


    @Override
    protected void onResume() {
        super.onResume();
        initData();
        Log.d(TAG, "onResume: intitData 실행?");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivaldetail);
        onAppBarSettings(true);
        setupBottomNavigationView();
    }

    @Override
    public void init() {
        festivalController = new FestivalController();
        ivImg = findViewById(R.id.ivImg);
        ivFav = findViewById(R.id.ivFav);
        ivComment = findViewById(R.id.ivComment);
        tvTitle = findViewById(R.id.tvTitle);
        mtvTraffic = findViewById(R.id.mtvTraffic);
        tvCountCom = findViewById(R.id.tvCounCom);
        mbtnCall = findViewById(R.id.mbtnCall);
        tvHomepage = findViewById(R.id.tvHomePage);
        mtvAdd = findViewById(R.id.mtvAdd);

    }

    @Override
    public void initLr() {

        ivComment.setOnClickListener(v->{

            Intent intent = new Intent(
                    mContext,
                    TourCommentActivity.class
            );
            startActivity(intent);
        });

    }

    @Override
    public void initSetting() {
        Intent getIntent = getIntent();
        festivalId = getIntent.getIntExtra("festivalId",0);
        if(festivalId == 0) finish();

    }

    @Override
    public void initData() {
        festivalController.findById(festivalId).enqueue(new Callback<CMRespDto<Festival>>() {
            @Override
            public void onResponse(Call<CMRespDto<Festival>> call, Response<CMRespDto<Festival>> response) {
                CMRespDto<Festival> cm = response.body();
                tvTitle.append(cm.getData().getFestivalTitle());
                mtvTraffic.append(cm.getData().getTraffic());
                mtvAdd.append(cm.getData().getFestivalAddr());
                tvHomepage.append(cm.getData().getHomepage());

                Glide.with(mContext)
                        .load(cm.getData().getThumb())
                        .centerCrop()
                        .placeholder(R.drawable.haeundae)
                        .into(ivImg);

            }

            @Override
            public void onFailure(Call<CMRespDto<Festival>> call, Throwable t) {
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