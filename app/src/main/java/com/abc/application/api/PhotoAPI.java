package com.abc.application.api;

import com.abc.application.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Nikhil Arora on 05-07-2017.
 */

public interface PhotoAPI {

    @GET("photos")
    Call<ArrayList<Photo>> getPhotos();

    @GET("/albums/{id}/photos")
    Call<ArrayList<Photo>>getPhotosbyId(
            @Path("id")int id
    );
}
