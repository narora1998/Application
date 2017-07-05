package com.abc.application.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abc.application.R;
import com.abc.application.models.Comment;

import java.util.ArrayList;

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewHolder>{

    Context context;
    ArrayList<Comment> commentArrayList;

    public void updateComments(ArrayList<Comment> newCommentList) {
        this.commentArrayList = newCommentList;
        notifyDataSetChanged();
    }

    public CommentRecyclerAdapter(Context context, ArrayList<Comment> commentArrayList) {
        this.context = context;
        this.commentArrayList = commentArrayList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.comment_layout , parent , false);
        return new CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {

        Comment thiscomment = commentArrayList.get(position);
        holder.body.setText(thiscomment.getBody());
        holder.name.setText(thiscomment.getName());
        holder.email.setText(thiscomment.getEmail());

    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView body , email , name;
        public CommentViewHolder(View itemView) {
            super(itemView);

            body = (TextView)itemView.findViewById(R.id.body);
            email = (TextView)itemView.findViewById(R.id.email);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }

}
