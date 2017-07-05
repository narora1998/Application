package com.abc.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abc.application.PostActivity;
import com.abc.application.R;
import com.abc.application.TodoActivity;
import com.abc.application.interfaces.OnItemClickListner;
import com.abc.application.models.User;

import java.util.ArrayList;

/**
 * Created by Nikhil Arora on 30-06-2017.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder> {

    Context context;
    ArrayList<User> users;
    OnItemClickListner OnItemClickListner;
    private OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }



    public UserRecyclerAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    public void updateUsers(ArrayList<User> newUserList) {
        this.users = newUserList;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.user_layout , parent , false);
        return  new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        final User thisUser = users.get(position);
        holder.username.setText(thisUser.getName());
        holder.name.setText(thisUser.getUsername());
        holder.phone.setText(thisUser.getPhone());
        holder.email.setText(thisUser.getEmail());
        /*holder.gotopost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListner != null){
                    onItemClickListner.onItemClick(thisUser.getId());
                }
            }
        });
        holder.gototodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListner!=null)
                onItemClickListner.onItemClick(thisUser.getId());
            }
        });*/
        holder.gotopost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , PostActivity.class);
                i.putExtra("UserId",thisUser.getId());
                context.startActivity(i);


            }
        });


        holder.gototodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , TodoActivity.class);
                i.putExtra("UserId",thisUser.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{

        TextView username , name , phone , email;
        Button gotopost , gototodo;
        View thisView;

        public UserViewHolder(View itemView) {

            super(itemView);
            thisView = itemView;
            username = (TextView)itemView.findViewById(R.id.username);
            name = (TextView)itemView.findViewById(R.id.name);
            phone = (TextView)itemView.findViewById(R.id.phone);
            email = (TextView)itemView.findViewById(R.id.email);
            gotopost = (Button)itemView.findViewById(R.id.gotopost);
            gototodo = (Button)itemView.findViewById(R.id.gototodo);
        }
    }
}
