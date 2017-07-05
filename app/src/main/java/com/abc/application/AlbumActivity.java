package com.abc.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abc.application.adapter.AlbumRecyclerAdapter;
import com.abc.application.api.AlbumAPI;
import com.abc.application.models.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumActivity extends AppCompatActivity {

    RecyclerView rvalbumlist;
    AlbumRecyclerAdapter albumRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        rvalbumlist = (RecyclerView)findViewById(R.id.rvalbumlist);
        rvalbumlist.setLayoutManager(new LinearLayoutManager(this));
        albumRecyclerAdapter = new AlbumRecyclerAdapter(this , new ArrayList<Album>());
        rvalbumlist.setAdapter(albumRecyclerAdapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        final AlbumAPI albumAPI = retrofit.create(AlbumAPI.class);

        albumAPI.getAlbums().enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call,
                                   Response<ArrayList<Album>> response) {
                albumRecyclerAdapter.updateAlbums(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

            }
        });
    }
}
