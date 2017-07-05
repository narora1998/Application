package com.abc.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.application.PhotoActivity;
import com.abc.application.R;
import com.abc.application.models.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nikhil Arora on 02-07-2017.
 */

public class ThumbnailRecyclerAdapter extends RecyclerView.Adapter<ThumbnailRecyclerAdapter.ThumbnailViewHolder> {

    Context context;
    ArrayList<Thumbnail> thumbnailArrayList;

    public ThumbnailRecyclerAdapter(Context context, ArrayList<Thumbnail> thumbnailArrayList) {
        this.context = context;
        this.thumbnailArrayList = thumbnailArrayList;
    }

    public void updateThumbnails(ArrayList<Thumbnail> newThumbnailList) {
        this.thumbnailArrayList = newThumbnailList;
        notifyDataSetChanged();
    }

    @Override
    public ThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.thumbnail_layout , parent , false);
        return new ThumbnailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ThumbnailViewHolder holder, int position) {

        final Thumbnail thisthumbnail = thumbnailArrayList.get(position);
        holder.title.setText(thisthumbnail.getTitle());
        String url = thisthumbnail.getThumbnailUrl();
        Picasso.with(context).load(url).into(holder.thumbnail);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , PhotoActivity.class);
                i.putExtra("url" , thisthumbnail.getUrl());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return thumbnailArrayList.size();
    }

    static class ThumbnailViewHolder extends RecyclerView.ViewHolder{

        TextView title , thumbnailUrl;
        ImageView thumbnail;
        View rootView;
        public ThumbnailViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            title = (TextView)itemView.findViewById(R.id.title);
            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);

        }
    }
}
