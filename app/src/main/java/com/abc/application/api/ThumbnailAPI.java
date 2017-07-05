package com.abc.application.api;

import com.abc.application.models.Thumbnail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Nikhil Arora on 05-07-2017.
 */

public interface  ThumbnailAPI {

        @GET("/photos")
        Call<ArrayList<Thumbnail>> getThumbnail();

        @GET("/albums/{id}/photos")
        Call<ArrayList<Thumbnail>>getThumbnailsbyid(
                @Path("id")int id
        );
}
