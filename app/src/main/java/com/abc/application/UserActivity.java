package com.abc.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abc.application.adapter.UserRecyclerAdapter;
import com.abc.application.api.UsersAPI;
import com.abc.application.interfaces.OnItemClickListner;
import com.abc.application.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {

    RecyclerView rvuserlist;
    UserRecyclerAdapter userRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        rvuserlist = (RecyclerView)findViewById(R.id.rvuserlist);
        rvuserlist.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerAdapter = new UserRecyclerAdapter(this , new ArrayList<User>());
        rvuserlist.setAdapter(userRecyclerAdapter);


        userRecyclerAdapter.setOnItemClickListner(new OnItemClickListner() {
            @Override
            public void onItemClick(int itemId) {
                Intent postActIntent = new Intent(UserActivity.this,
                        PostActivity.class);

                postActIntent.putExtra("userId", itemId);
                startActivity(postActIntent);

            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        final UsersAPI usersAPI = retrofit.create(UsersAPI.class);

        usersAPI.getUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call,
                                   Response<ArrayList<User>> response) {
                userRecyclerAdapter.updateUsers(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });

    }

}
