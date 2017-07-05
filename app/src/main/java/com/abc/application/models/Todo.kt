package com.abc.application.models

/**
 * Created by Nikhil Arora on 01-07-2017.
 */

data class Todo(
        val id: Int,
        val userId: Int,
        val title: String,
        val completed: String
)