package com.example.networkwithretrofit.network

import com.example.networkwithretrofit.model.*
import retrofit2.Call
import retrofit2.http.*

interface RestfulApi {
    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @POST("news")
    fun postDataNews(@Body request : DataNews) : Call<ResponseAddNews>

    @PUT("news/{id}")
    fun updateDataNews(
        @Path("id") id : Int,
        @Body request : DataNews
        ) : Call<List<ResponseUpdateNews>>

    @GET("news/{id}")
    fun getDetailFilm(@Path("id") id : Int): Call<List<ResponseDataFilmItem>>

    @DELETE("news/{id}")
    fun deleteNews(@Path("id") id: Int) : Call<Int>


    @GET("film")
    fun getAllFilm(): Call<List<ResponseDataFilmItem>>




}