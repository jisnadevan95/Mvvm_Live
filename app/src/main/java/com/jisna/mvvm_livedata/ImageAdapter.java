package com.jisna.mvvm_livedata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.HeroViewHolder> {

    Context mCtx;
    List<ResponseClass> heroList;

    public ImageAdapter(Context mCtx, List<ResponseClass> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        ResponseClass responseclass = heroList.get(position);

        Glide.with(mCtx)
                .load(responseclass.getThumbnailUrl())
                .into(holder.imageView);
        Glide.with(mCtx)
                .load(responseclass.getUrl())
                .into(holder.imageView2);

        holder.textView.setText(responseclass.getTitle());
        holder.thumbId.setText(String.valueOf(responseclass.getId()));
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,imageView2;
        TextView textView,thumbId;

        public HeroViewHolder(View itemView) {
            super(itemView);
            imageView2 = itemView.findViewById(R.id.imageView2);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            thumbId = itemView.findViewById(R.id.thumbId);
        }
    }

    {
    }
}
