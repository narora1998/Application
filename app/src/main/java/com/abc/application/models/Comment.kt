package com.abc.application.models

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

data class Comment(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
)
