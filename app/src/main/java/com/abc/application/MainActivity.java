package com.abc.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvuser , tvpost , tvalbum , tvtodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();



        tvuser = (TextView)findViewById(R.id.tvuser);
        tvpost = (TextView)findViewById(R.id.tvpost);
        tvalbum = (TextView)findViewById(R.id.tvalbum);
        tvtodo = (TextView)findViewById(R.id.tvtodo);

        tvuser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , UserActivity.class);
                startActivity(i);
            }
        });



        tvpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this , PostActivity.class);
                startActivity(i);
            }
        });

       tvalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , AlbumActivity.class);
                startActivity(i);
            }
        });

        tvtodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , TodoActivity.class);
                startActivity(i);
            }
        });
    }

}
