package com.com.busantourisme.view.get.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.com.busantourisme.R;
import com.com.busantourisme.model.tour.TourDetail;
import com.com.busantourisme.view.get.Tour.TourCommentActivity;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHoler> {

    private static final String TAG = "DetailAdapter";
    private TourCommentActivity mContext;
    private CommentAdapter commentAdapter= this;
    private List<TourDetail> details = new ArrayList<>();

    public CommentAdapter(TourCommentActivity mContext) {
        this.mContext = mContext;
    }

    public void addItmes(List<TourDetail> details){
        this.details = details;
        notifyDataSetChanged();
    }


    //문법
    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.comment_item,parent,false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
        TourDetail detail = details.get(position);
        holder.setItem(detail);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class MyViewHoler extends RecyclerView.ViewHolder{

        private ImageView ivFav,ivComment;
        private TextView tvCounFav,tvCounCom;

        public MyViewHoler(View itemView) {
            super(itemView);
            ivFav = itemView.findViewById(R.id.ivFav);
            ivComment = itemView.findViewById(R.id.ivComment);
            tvCounFav = itemView.findViewById(R.id.tvCounFav);
            tvCounCom = itemView.findViewById(R.id.tvCounCom);
        }

        public void setItem(TourDetail detail){
            ivFav.setImageResource(detail.getFavorite());
            ivComment.setImageResource(detail.getComment());
            tvCounFav.setText(detail.getCountFav());
            tvCounCom.setText(detail.getCountCom());
        }


    }
}
