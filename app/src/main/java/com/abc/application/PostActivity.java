package com.abc.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abc.application.adapter.PostRecyclerAdapter;
import com.abc.application.api.PostAPI;
import com.abc.application.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    RecyclerView rvpostlist;
    PostRecyclerAdapter postRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        rvpostlist = (RecyclerView)findViewById(R.id.rvpostlist);
        rvpostlist.setLayoutManager(new LinearLayoutManager(this));
        postRecyclerAdapter = new PostRecyclerAdapter(this , new ArrayList<Post>());
        rvpostlist.setAdapter(postRecyclerAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        /*final PostAPI postAPI = retrofit.create(PostAPI.class);

        postAPI.getPosts().enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call,
                                   Response<ArrayList<Post>> response) {
                postRecyclerAdapter.updatePosts(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        });*/

        PostAPI postsAPI = retrofit.create(PostAPI.class);
        Callback<ArrayList<Post>> postCallback = new Callback<ArrayList<Post>>() {

            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postRecyclerAdapter.updatePosts(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        };
        int userIdReceived = getIntent().getIntExtra("UserId", -1);
        if (userIdReceived != -1) {
            postsAPI.getPostsByUserId(userIdReceived).enqueue(postCallback);
        } else {
            postsAPI.getPosts().enqueue(postCallback);
        }
    }
}
