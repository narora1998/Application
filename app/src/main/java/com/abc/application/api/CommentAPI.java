package com.abc.application.api;

import com.abc.application.models.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

public interface CommentAPI {

    @GET("/comments")
    Call<ArrayList<Comment>> getComments ();

    @GET("/comments")
    Call<ArrayList<Comment>> getCommentsByPostId (
            @Query("postId") int postId
    );
}
