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
import com.com.busantourisme.model.festival.Festival;
import com.com.busantourisme.view.get.festival.FestivalActivity;
import com.com.busantourisme.view.get.festival.FestivalDetailActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


//2.어댑터 만들기
public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.MyViewHolder> {

    private static final String TAG = "FestivalAdapter";
    //MainAdapter 생성자에 접근하기 위한 변수 (mContext가 rvTours를 들고 있다)
    private static Context mContext;
    private FestivalAdapter festivalAdapter = this;

    private static List<Festival> festivals = new ArrayList<>();
    private static final int ACTVINT_NUM = 1;


    public FestivalAdapter(FestivalActivity mContext,List<Festival> festivals) {
        this.mContext = mContext;
        this.festivals = festivals;

    }

    public FestivalAdapter(FestivalActivity mContext) {
        this.mContext = mContext;

    }

    //최초 등록,데이터 세팅
    public void addItems(List<Festival> festivals) {
        this.festivals = festivals;
        notifyDataSetChanged();
    }
    //    //한건 삭제
//    public void removeItem(int index){
//        this.tours.remove(index);
//        notifyDataSetChanged();//데이터 변경하는곳에는 무조건 걸어줘야함
//    }
////    //한건 추가
//    public void addItem(Festival festivals){
//        this.festivals.add(festivals);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.festival_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            Festival festival = festivals.get(position);
            holder.setItem(festival);

        Glide.with(mContext)
                .load(festival.getThumb())
                .centerCrop()
                .placeholder(R.drawable.haeundae)
                .into(holder.ivGlide);
    }


    //데이터 갈아끼움

    @Override
    public int getItemCount() {
        return festivals.size();
    }

    //1. 뷰홀더 만들기(데이터 끼워 넣게하는 것)
    //최초에 n개 만듬.
    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivGlide;
        private TextView tvTitle,tvUsageDay;
        private MaterialButton mbtnDetail;

        //앱 구동시 발동
        public MyViewHolder(View itemView) {
            super(itemView);
            ivGlide = itemView.findViewById(R.id.ivGlide);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvUsageDay = itemView.findViewById(R.id.tvUsageDay);
            mbtnDetail = itemView.findViewById(R.id.mbtnDetail);

            initLr();
        }

        private void initLr(){
            //다른 클래스임으로 Tours를 찾을 수 없다.
            //super가 부모에서 변수로 받고 있어서 자식이 사용가능
           mbtnDetail.setOnClickListener(v->{
               Festival festival = festivals.get(getAdapterPosition());
               Intent intent = new Intent(
                       mContext,
                       FestivalDetailActivity.class
               );
               Log.d(TAG, "initLr:intent 읽기 ");
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.putExtra("festivalId",festival.getFestivalId());
               mContext.startActivity(intent);
           });

        }

        //앱 구동시 + 스크롤 할 때 작동
        public void setItem(Festival festival){
            //Drawable type
        tvTitle.setText(festival.getFestivalTitle());
        tvUsageDay.setText(festival.getUsageDay());

        }
    }
}
