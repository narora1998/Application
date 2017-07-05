package com.abc.application.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.application.R;
import com.abc.application.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nikhil Arora on 05-07-2017.
 */

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoViewHolder>{

    Context context;
    ArrayList<Photo> photoArrayList;

    public PhotoRecyclerAdapter(Context context, ArrayList<Photo> photoArrayList) {
        this.context = context;
        this.photoArrayList = photoArrayList;
    }

    public void updatePhotos(ArrayList<Photo> newPhotoList) {
        this.photoArrayList = newPhotoList;
        notifyDataSetChanged();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.photo_layout , parent , false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        Photo thisphoto = photoArrayList.get(position);
        holder.title.setText(thisphoto.getTitle());
        String url = thisphoto.getUrl();
        Picasso.with(context).load(url).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView photo;
        public PhotoViewHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title);
            photo = (ImageView)itemView.findViewById(R.id.photo);
        }
    }
}
