package com.abc.application.api;

import com.abc.application.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nikhil Arora on 30-06-2017.
 */

public interface UsersAPI{
    @GET("/users")
    Call<ArrayList<User>> getUsers();
}