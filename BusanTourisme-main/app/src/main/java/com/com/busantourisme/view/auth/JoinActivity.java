package com.com.busantourisme.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.com.busantourisme.InitMethod;
import com.com.busantourisme.R;
import com.com.busantourisme.controller.AuthController;
import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.controller.Dto.JoinDto;
import com.com.busantourisme.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity implements InitMethod {

    private JoinActivity mContext = JoinActivity.this;
    private static final String TAG = "JoinActivity";

    private AuthController authController;
    private TextInputEditText tvUsername,tvPassword,tvEamil;
    private MaterialButton btnJoin;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        init();
        initLr();
        initSetting();
    }

    @Override
    public void init() {
        authController = new AuthController();
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        tvEamil = findViewById(R.id.tvEmail);
        btnJoin = findViewById(R.id.btnJoin);
        tvLogin = findViewById(R.id.tvLogin);
    }

    @Override
    public void initLr() {

        //회원가입 버튼 클릭 이벤트(콜백):start
        btnJoin.setOnClickListener(v->{
            String username = tvUsername.getText().toString().trim();
            String password = tvPassword.getText().toString().trim();
            String email = tvEamil.getText().toString().trim();
            authController.join(new JoinDto(username,password,email)).enqueue(new Callback<CMRespDto<User>>() {
                @Override
                public void onResponse(Call<CMRespDto<User>> call, Response<CMRespDto<User>> response) {
                    CMRespDto<User> cm  = response.body();
                    Log.d(TAG, "onResponse: cm.getCode()"+cm.getCode());
                    Log.d(TAG, "onResponse: cm.getData()"+cm.getData());

                    Intent intent = new Intent(
                      mContext,
                      LoginActivity.class
                    );
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<CMRespDto<User>> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        });
//회원가입 버튼 클릭 이벤트(콜백) :end

        tvLogin.setOnClickListener(v->{
            Intent intent = new Intent(
                    mContext,
                    LoginActivity.class
            );
        });

    }//initLr

    @Override
    public void initSetting() {

    }
}