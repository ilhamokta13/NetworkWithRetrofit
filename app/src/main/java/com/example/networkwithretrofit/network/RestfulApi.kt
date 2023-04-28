package com.example.networkwithretrofit.network

import com.example.networkwithretrofit.model.ResponseDataFilmItem
import com.example.networkwithretrofit.model.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {
    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("film")
    fun getAllFilm(): Call<List<ResponseDataFilmItem>>




}