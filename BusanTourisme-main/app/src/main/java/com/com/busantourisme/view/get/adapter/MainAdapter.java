package com.com.busantourisme.view.get.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.com.busantourisme.R;
import com.com.busantourisme.model.tour.Tour;
import com.com.busantourisme.view.get.Tour.TourDetailActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


//2.어댑터 만들기
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private static final String TAG = "MainAdapter";
    //MainAdapter 생성자에 접근하기 위한 변수 (mContext가 rvTours를 들고 있다
    private static Context mContext;
    private MainAdapter mainAdapter = this;

    private static List<Tour> tours = new ArrayList<>();
    private static final int ACTVINT_NUM = 1;



    public MainAdapter(Context mContext, List<Tour> tours) {
        this.mContext = mContext;
        this.tours = tours;

    }

    public MainAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //최초 등록,데이터 세팅
    public void addItems(List<Tour> tours) {
        this.tours = tours;
        notifyDataSetChanged();
    }
//    //한건 삭제
//    public void removeItem(int index){
//        this.tours.remove(index);
//        notifyDataSetChanged();//데이터 변경하는곳에는 무조건 걸어줘야함
//    }
//    //한건 추가
    public void addItem(Tour tour){
        this.tours.add(tour);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_item,parent,false);
        return new MyViewHolder(view);
    }


    //데이터 갈아끼움
    @Override
    public void onBindViewHolder(MainAdapter.MyViewHolder holder, int position) {
        Tour tour = tours.get(position);
        holder.setItem(tour);
//        holder.setGlide(tour);


        Glide.with(mContext)
                .load(tour.getThumb())
                .centerCrop()
                .placeholder(R.drawable.haeundae)
                .into(holder.ivGlide);
//
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    //1. 뷰홀더 만들기(데이터 끼워 넣게하는 것)
    //최초에 n개 만듬.
    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivGlide;
        private TextView tvTitle;
        private MaterialButton btnDetail;



        //앱 구동시 발동
        public MyViewHolder(View itemView) {
            super(itemView);
            ivGlide = itemView.findViewById(R.id.ivGlide);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnDetail = itemView.findViewById(R.id.btnDetail);


            initLr();
        }

       private void initLr(){
            //다른 클래스임으로 Tours를 찾을 수 없다.
            //super가 부모에서 변수로 받고 있어서 자식이 사용가능
           btnDetail.setOnClickListener(v->{
               Tour tour = tours.get(getAdapterPosition());
               Log.d(TAG, "initLr: tour.getId() :"+tour.getTourId());
               Intent intent = new Intent(
                       mContext,
                       TourDetailActivity.class
                       );
               Log.d(TAG, "initLr:intent 읽기 ");
               intent.putExtra("tourId",tour.getTourId());
               intent.putExtra("destLat",tour.getLat());
               intent.putExtra("destLng",tour.getLng());
               mContext.startActivity(intent);
           });

        }

        //앱 구동시 + 스크롤 할 때 작동
        public void setItem(Tour tour){
            //Drawable type
//            ivImg.setImageResource(tour.getTourImageUrl());
//            tvTitle.setText(tour.getTourName());
//------------------------------------------------------------------
        tvTitle.setText(tour.getTourTitle());

        }


    }
}
