package com.roytho.live.photos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.roytho.live.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    ArrayList<PhotoItem> photoItems;
    Context context;

    public PhotoAdapter(Context context, ArrayList<PhotoItem> feedItems, RecyclerView rv){
        this.photoItems = feedItems;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.photo_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PhotoItem current = photoItems.get(position);

            if(current.getUrl() == ""){
                Picasso.with(context).load(R.drawable.stc_cover_res).into(holder.ThumbNail);
            } else {
                Picasso.with(context).load(current.getUrl()).into(holder.ThumbNail);
            }

    }

    @Override
    public int getItemCount() {
        if(photoItems == null){
            return 0;
        } else {
            return photoItems.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ThumbNail;

        public MyViewHolder(View itemView) {
            super(itemView);
            ThumbNail = (ImageView) itemView.findViewById(R.id.ivImage);
        }

    }

}
