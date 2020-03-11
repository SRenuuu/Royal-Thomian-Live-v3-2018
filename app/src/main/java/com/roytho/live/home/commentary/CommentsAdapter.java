package com.roytho.live.home.commentary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.roytho.live.R;

import java.util.Collections;
import java.util.List;


public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;
    List<Comment> data= Collections.emptyList();
    Comment current;

    public CommentsAdapter(Context context, List<Comment> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.comment_item, parent, false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder= (MyHolder) holder;
        current=data.get(position);

        switch(current.getState()){
            case "none":
                myHolder.tvState.setVisibility(View.GONE);
                break;
            default:
                myHolder.tvState.setText(current.getState());
                break;
        }

        myHolder.tvComment.setText(current.getComment());
    }

    public void removeAt(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView tvComment, tvState;

        // create constructor to get widget reference
        public MyHolder(final View itemView) {
            super(itemView);

            tvComment = itemView.findViewById(R.id.tvCommItenText);
            tvState = itemView.findViewById(R.id.tvCommItemState);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

    }
}
