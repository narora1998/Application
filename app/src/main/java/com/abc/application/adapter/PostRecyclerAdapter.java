package com.abc.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abc.application.CommentActivity;
import com.abc.application.R;
import com.abc.application.models.Post;

import java.util.ArrayList;

/**
 * Created by Nikhil Arora on 30-06-2017.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder> {

    Context context;
    ArrayList<Post> posts;

    public PostRecyclerAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void updatePosts (ArrayList<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.post_layout , parent , false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        final Post thispost = posts.get(position);
        holder.title.setText(thispost.getTitle());
        holder.body.setText(thispost.getBody());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , CommentActivity.class);
                i.putExtra("PostId",thispost.getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder{

        TextView title , body;
        View rootView;
        public PostViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            title = (TextView)itemView.findViewById(R.id.title);
            body = (TextView)itemView.findViewById(R.id.body);
        }
    }
}
