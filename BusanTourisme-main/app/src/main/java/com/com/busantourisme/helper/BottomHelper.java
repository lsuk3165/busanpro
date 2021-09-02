package com.com.busantourisme.helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.com.busantourisme.R;
import com.com.busantourisme.view.get.festival.FestivalActivity;
import com.com.busantourisme.view.get.etcView.SearchActivity;
import com.com.busantourisme.view.get.etcView.UserInfoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomHelper {


    public static void enableBottomNavi(Context context, BottomNavigationView bottomNavigation) {
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    //클릭시 화면이동
                    if(item.getItemId()==R.id.navSearch){
                        Intent intent1 = new Intent(
                                context,
                                SearchActivity.class
                        );
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent1);
                    }else if(item.getItemId() == R.id.navEvent){
                        Intent intent2 = new Intent(
                                context,
                                FestivalActivity.class
                        );
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent2);


                    }else if (item.getItemId() == R.id.navInfo){
                        Intent intent3 = new Intent(context, UserInfoActivity.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent3);
                    }

                return true;
            }
        });
    }

}