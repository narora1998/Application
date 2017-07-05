package com.abc.application.api;

import com.abc.application.models.Comment;
import com.abc.application.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nikhil Arora on 30-06-2017.
 */

public interface PostAPI {

    @GET("/posts")
    Call<ArrayList<Post>> getPosts();

    @GET("/posts/{id}")
    Call<Post> getPostById (
            @Path("id") int id
    );

    @GET("/posts")
    Call<ArrayList<Post>> getPostsByUserId (
            @Query("userId") int userId
    );

    interface CommentsAPI {

        @GET("/posts/{postId}/comments")
        Call<ArrayList<Comment>> getCommentsOfPostId (
                @Path("postId") int postId
        );

    }
}
