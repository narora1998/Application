package com.abc.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abc.application.adapter.PhotoRecyclerAdapter;
import com.abc.application.api.PhotoAPI;
import com.abc.application.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoActivity extends AppCompatActivity {

    RecyclerView rvphotolist;
    PhotoRecyclerAdapter photoRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        rvphotolist = (RecyclerView) findViewById(R.id.rvphotolist);
        photoRecyclerAdapter = new PhotoRecyclerAdapter(this, new ArrayList<Photo>());
        rvphotolist.setLayoutManager(new LinearLayoutManager(this));
        rvphotolist.setAdapter(photoRecyclerAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();


        PhotoAPI photoAPI = retrofit.create(PhotoAPI.class);
        Callback<ArrayList<Photo>> PhotoCallback = new Callback<ArrayList<Photo>>() {

            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                photoRecyclerAdapter.updatePhotos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        };
        int UrlReceived = getIntent().getIntExtra("url", -1);
        if (UrlReceived != -1)
            photoAPI.getPhotosbyId(UrlReceived).enqueue(PhotoCallback);
    }
}
