package com.com.busantourisme.controller;

import android.util.Log;

import com.com.busantourisme.controller.Dto.CMRespDto;
import com.com.busantourisme.model.festival.Festival;
import com.com.busantourisme.model.tour.Tour;
import com.com.busantourisme.service.FestivalService;
import com.com.busantourisme.service.TourService;

import java.util.List;

import retrofit2.Call;

public class FestivalController {

    private static final String TAG = "FestivalController";
    private FestivalService festivalService = FestivalService.festivalService;


    //관광지 요청
    public Call<CMRespDto<List<Festival>>> findAll(){
        Log.d(TAG, "findAll: 실행");
        return festivalService.findAll();
    }

    public Call<CMRespDto<Festival>> findById(int festivalId){
        Log.d(TAG, "findAll: findOne");
        return festivalService.findById(festivalId);
    }



}
