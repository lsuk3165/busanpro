package com.com.busantourisme.service;


import com.com.busantourisme.config.SessionInterceptor;
import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.model.tour.Tour;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface TourService {


    //메인페이지
    @GET("/tour/list")
    Call<CMRespDto<List<Tour>>> findAll();
    
    //Detail 화면으로 갈때
    @GET("/tour/{tourId}")
    Call<CMRespDto<Tour>> findById(@Path("tourId") int tourId);

    @GET("/tour/area/{area}")
    Call<CMRespDto<List<Tour>>> findArea(@Path("area") String area);




// .addInterceptor(new SessionInterceptor()).build();
//    OkHttpClient client = new OkHttpClient.Builder()
//            .connectTimeout(1, TimeUnit.MINUTES)
//        .addInterceptor(null).build();
//

//    OkHttpClient okHttpClient = new OkHttpClient.Builder()
//        .connectTimeout(1, TimeUnit.MINUTES)
//        .readTimeout(30, TimeUnit.SECONDS)
//        .writeTimeout(15, TimeUnit.SECONDS)
//        .build();


    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.220.16:8005")

//            .client(client)
            .build();


    TourService service = retrofit.create(TourService.class);

}
