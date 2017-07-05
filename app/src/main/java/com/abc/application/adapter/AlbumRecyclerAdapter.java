package com.abc.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abc.application.R;
import com.abc.application.ThumbnailActivity;
import com.abc.application.models.Album;

import java.util.ArrayList;

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public void updateAlbums (ArrayList<Album> albumArrayList) {
        this.albumArrayList = albumArrayList;
        notifyDataSetChanged();
    }

    public AlbumRecyclerAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.album_layout , parent , false);
        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

        final Album thisalbum = albumArrayList.get(position);
        holder.title.setText(thisalbum.getTitle());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , ThumbnailActivity.class);
                i.putExtra("id",thisalbum.getUserId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        View rootView;
        public AlbumViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
