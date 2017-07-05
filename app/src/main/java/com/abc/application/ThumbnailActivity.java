package com.abc.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abc.application.adapter.ThumbnailRecyclerAdapter;
import com.abc.application.api.ThumbnailAPI;
import com.abc.application.models.Thumbnail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThumbnailActivity extends AppCompatActivity {

    RecyclerView rvthumbnaillist;
    ThumbnailRecyclerAdapter thumbnailRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        rvthumbnaillist = (RecyclerView)findViewById(R.id.rvthumbnailist);
        thumbnailRecyclerAdapter = new ThumbnailRecyclerAdapter(this , new ArrayList<Thumbnail>());
        rvthumbnaillist.setLayoutManager(new LinearLayoutManager(this));
        rvthumbnaillist.setAdapter(thumbnailRecyclerAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        ThumbnailAPI thumbnailAPI = retrofit.create(ThumbnailAPI.class);
        Callback<ArrayList<Thumbnail>> ThumbnailCallback = new Callback<ArrayList<Thumbnail>>() {

            @Override
            public void onResponse(Call<ArrayList<Thumbnail>> call, Response<ArrayList<Thumbnail>> response) {
                thumbnailRecyclerAdapter.updateThumbnails(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Thumbnail>> call, Throwable t) {

            }
        };
        int userIdReceived = getIntent().getIntExtra("id", -1);
        if (userIdReceived != -1) {
            thumbnailAPI.getThumbnailsbyid(userIdReceived).enqueue(ThumbnailCallback);
        }
    }
}
