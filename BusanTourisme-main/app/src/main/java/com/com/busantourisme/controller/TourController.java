package com.com.busantourisme.controller;

import android.util.Log;

import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.controller.Dto.JoinDto;
import com.com.busantourisme.model.User;
import com.com.busantourisme.model.tour.Tour;
import com.com.busantourisme.service.TourService;

import java.util.List;

import retrofit2.Call;

public class TourController {
    private static final String TAG = "TourController";
    private TourService tourService = TourService.service;

    //관광지 요청
    public Call<CMRespDto<List<Tour>>> findAll(){
        Log.d(TAG, "findAll: 실행");
        return tourService.findAll();
    }

    public Call<CMRespDto<Tour>> findById(int tourId){
        Log.d(TAG, "findAll: findOne");
        return tourService.findById(tourId);
    }

    //해운대 관광지
    public Call<CMRespDto<List<Tour>>> findArea(String area){
        Log.d(TAG, "findHeaundae: 실행");
        return tourService.findArea(area);
    }
}
