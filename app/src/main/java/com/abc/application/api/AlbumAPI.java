package com.abc.application.api;

import com.abc.application.models.Album;
import com.abc.application.models.Thumbnail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

public interface AlbumAPI {

    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();


    interface PhotosApi{
        @GET("/photos")
        Call<ArrayList<Thumbnail>>getThumbnail();

        @GET("/albums/{id}/photos")
        Call<ArrayList<Thumbnail>>getPhotosOfAlbumId(
                @Path("id")int id
        );
    }

}
