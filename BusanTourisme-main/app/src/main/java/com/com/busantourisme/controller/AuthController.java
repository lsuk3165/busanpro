package com.com.busantourisme.controller;

import android.util.Log;

import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.controller.Dto.JoinDto;
import com.com.busantourisme.controller.Dto.LoginDto;
import com.com.busantourisme.model.User;
import com.com.busantourisme.service.UserService;


import retrofit2.Call;

public class AuthController {
    private static final String TAG = "AuthController";
    private UserService userService = UserService.service;

    //로그인
    public Call<CMRespDto<User>> login(LoginDto loginDto){
        Log.d(TAG, "login: "+loginDto);
        return userService.login(loginDto);
    }

    //회원가입
    public Call<CMRespDto<User>> join(JoinDto joinDto){
        Log.d(TAG, "join: joinDto: "+joinDto);
        return userService.join(joinDto);
    }
}
