package com.abc.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abc.application.adapter.TodoRecyclerAdapter;
import com.abc.application.api.TodoAPI;
import com.abc.application.models.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoActivity extends AppCompatActivity {

    RecyclerView rvtodolist;
    TodoRecyclerAdapter todoRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        rvtodolist = (RecyclerView)findViewById(R.id.rvtodolist);
        rvtodolist.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerAdapter = new TodoRecyclerAdapter(this , new ArrayList<Todo>());
        rvtodolist.setAdapter(todoRecyclerAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();


       /* final TodoAPI todoAPI = retrofit.create(TodoAPI.class);

        todoAPI.getTodos().enqueue(new Callback<ArrayList<Todo>>() {
            @Override
            public void onResponse(Call<ArrayList<Todo>> call,
                                   Response<ArrayList<Todo>> response) {
                todoRecyclerAdapter.updateTodos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {

            }
        });*/

        TodoAPI todoAPI = retrofit.create(TodoAPI.class);
        Callback<ArrayList<Todo>> TodoCallback = new Callback<ArrayList<Todo>>() {

            @Override
            public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                todoRecyclerAdapter.updateTodos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {

            }
        };
        int userIdReceived = getIntent().getIntExtra("UserId", -1);
        if (userIdReceived != -1) {
            todoAPI.getTodosByUserId(userIdReceived).enqueue(TodoCallback);
        } else {
            todoAPI.getTodos().enqueue(TodoCallback);
        }
    }
}
