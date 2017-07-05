package com.abc.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abc.application.adapter.CommentRecyclerAdapter;
import com.abc.application.api.CommentAPI;
import com.abc.application.models.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {

    RecyclerView rvcommentlist;
    CommentRecyclerAdapter commentRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        rvcommentlist = (RecyclerView)findViewById(R.id.rvcommentlist);
        commentRecyclerAdapter = new CommentRecyclerAdapter(this , new ArrayList<Comment>());
        rvcommentlist.setLayoutManager(new LinearLayoutManager(this));
        rvcommentlist.setAdapter(commentRecyclerAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        CommentAPI commentAPI = retrofit.create(CommentAPI.class);
        Callback<ArrayList<Comment>> CommentCallback = new Callback<ArrayList<Comment>>() {

            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                commentRecyclerAdapter.updateComments(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        };
        int userIdReceived = getIntent().getIntExtra("PostId", -1);
        if (userIdReceived != -1) {
            commentAPI.getCommentsByPostId(userIdReceived).enqueue(CommentCallback);
        } else {
            commentAPI.getComments().enqueue(CommentCallback);
        }


    }
}
