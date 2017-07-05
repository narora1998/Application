package com.abc.application.api;

import com.abc.application.models.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

public interface TodoAPI {
    @GET("/todos")
    Call<ArrayList<Todo>> getTodos();

    @GET("/todos/{id}")
    Call<Todo> getTodosById (
            @Path("id") int id
    );

    @GET("/todos")
    Call<ArrayList<Todo>> getTodosByUserId (
            @Query("userId") int userId
    );
}
