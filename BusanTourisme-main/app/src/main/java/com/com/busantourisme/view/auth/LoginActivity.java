package com.com.busantourisme.view.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.com.busantourisme.InitMethod;
import com.com.busantourisme.R;
import com.com.busantourisme.controller.AuthController;

public class LoginActivity extends AppCompatActivity implements InitMethod {


    private static final String TAG = "LoginActivity";
    private LoginActivity mContext = LoginActivity.this;
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void init() {

    }

    @Override
    public void initLr() {

    }

    @Override
    public void initSetting() {

    }
}