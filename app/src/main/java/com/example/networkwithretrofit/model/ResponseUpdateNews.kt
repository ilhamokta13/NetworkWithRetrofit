package com.example.networkwithretrofit.model

import com.google.gson.annotations.SerializedName

data class ResponseUpdateNews(
    @SerializedName("author")
    val author: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("username")
    val username: String
)
