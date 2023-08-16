package com.example.datktph26567_fn_mob304.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datktph26567_fn_mob304.Model.Giay;
import com.example.datktph26567_fn_mob304.R;

import java.util.List;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.GiayViewHolder> {
    private List<Giay> giayList;
    private OnItemClickListener listener;
    private OnDeleteClickListener deleteClickListener;
    private Context context;

    public GiayAdapter(List<Giay> giayList, OnItemClickListener listener) {
        this.giayList = giayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GiayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lover_item, parent, false);
        context = parent.getContext();
        return new GiayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiayViewHolder holder, int position) {
        final Giay giay =giayList.get(position);
        holder.tv_name.setText(giay.getName());
        holder.tv_hangSx.setText(giay.getHangSx());
        holder.tv_loai.setText(giay.getLoai());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(giay);
            }
        });
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(giay);
                }
            }
        });
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_animation));
    }

    @Override
    public int getItemCount() {
        return giayList.size();
    }

    public static  class GiayViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_hangSx,tv_loai,tv_size,tv_moTa,tv_del;
        CardView cardView;
        public GiayViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_name = itemView.findViewById(R.id.tv_name);
            tv_loai = itemView.findViewById(R.id.tv_loai);
            tv_hangSx = itemView.findViewById(R.id.tv_hangsx);
            tv_del = itemView.findViewById(R.id.tv_delete);
            cardView = itemView.findViewById(R.id.card1);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Giay giay);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Giay giay);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }
}
